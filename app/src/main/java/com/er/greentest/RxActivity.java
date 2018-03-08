package com.er.greentest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {
    CompositeDisposable comDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
//        comDisposable.dispose();
        test();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onRxClick(View view) {
        switch (view.getId()) {
            case R.id.normal:
                test();
//                test1();
                break;
            case R.id.backpressure:
                backPressureStrategy();
                break;
            case R.id.subscriptionRequest:
                request();
                break;
            case R.id.zip:
                zip();
                break;
        }
    }

    public void test() {

        Observable.just("hello...").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                Log.w("RX2", "---->>>>>>>>>>>>>>>>>apply："+Thread.currentThread());
                Thread.sleep(6000);
                return s.length();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.w("RX2", "---->>>>>>>>>>>>>>>>>onSubscribe "+Thread.currentThread());
                    }

                    @Override
                    public void onNext(Integer integer) {
//                        try {
//                            Thread.sleep(6000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        Toast.makeText(RxActivity.this, "aaaaaaa", Toast.LENGTH_SHORT).show();
                        Log.w("RX2", "---->>>>>>>>>>>>>>>>>onNext "+integer+" "+Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("RX2", "---->>>>>>>>>>>>>>>>>onError "+Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        Log.w("RX2", "---->>>>>>>>>>>>>>>>>onComplete "+Thread.currentThread());
                    }
                });
    }

    private void test1() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.w("RX2", ">>>>>>>>>>>>>>>>>subscribe");
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return integer + "d";
            }
        });


        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.w("RX2", ">>>>>>>>>>>>>>>>>onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.w("RX2", ">>>>>>>>>>>>>>>>>onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.w("RX2", ">>>>>>>>>>>>>>>>>onError");
            }

            @Override
            public void onComplete() {
                Log.w("RX2", ">>>>>>>>>>>>>>>>>onComplete");
            }
        };

        observable.subscribe(observer);

    }

    private Subscription subscription;

    Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
        @Override
        public void subscribe(@NonNull FlowableEmitter<Integer> e) throws Exception {
            int count = 0;
            while (true) {
                e.onNext(++count);
            }
        }
    }, BackpressureStrategy.LATEST);
    Subscriber<Integer> subscriber = new Subscriber<Integer>() {
        @Override
        public void onSubscribe(Subscription s) {
            Log.w("RX2", ">>>>>>>>>>>>>>>>>subscriber onSubscribe:" + (s == null) + " " + s.toString());
            subscription = s;
            s.request(10);
        }

        @Override
        public void onNext(Integer o) {
            Log.w("RX2", ">>>>>>>>>>>>>>>>>subscriber onNext:" + o);
        }

        @Override
        public void onError(Throwable t) {
            Log.w("RX2", ">>>>>>>>>>>>>>>>>subscriber onError:" + t.toString());
        }

        @Override
        public void onComplete() {

        }
    };

    private void backPressureStrategy() {
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private void request() {
        if (subscription != null) {
            subscription.request(10);
        }
    }


    private void zip() {
//        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                Log.w("RX2", "·····················integerObservable");
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onNext(4);
//                e.onNext(5);
//                e.onNext(6);
////                e.onComplete();
//            }
//        });
//
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.w("RX2", "·····················stringObservable " + Thread.currentThread());
                e.onNext("this is A");
                e.onNext("this is B");
                e.onNext("this is C");
                e.onComplete();
                e.isDisposed();
            }
        });

//        stringObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.w("RX2", "·····················doOnNext accept：" + Thread.currentThread());
//                    }
//                })
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.w("RX2", "·····················Observer onSubscribe：" + Thread.currentThread());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull String s) {
//                        Log.w("RX2", "·····················Observer onNext：" + Thread.currentThread());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.w("RX2", "·····················Observer onError：" + Thread.currentThread());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.w("RX2", "·····················Observer onComplete：" + Thread.currentThread());
//                    }
//                });


//        Observable.just(1, 2, 3, 4, 5, 6, 7).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer s) throws Exception {
//                        Log.w("RX2", "·····················doOnNext：" + Thread.currentThread());
//                    }
//                })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.w("RX2", "·····················onSubscribe：" + Thread.currentThread());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Integer s) {
//                        Log.w("RX2", "·····················onNext：" + Thread.currentThread());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.w("RX2", "·····················onError：" + Thread.currentThread());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.w("RX2", "·····················onComplete：" + Thread.currentThread());
//                    }
//                });

//        Observable.zip(integerObservable, stringObservable, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
//                return s+integer;
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.w("RX2", "·····················accept："+s);
//            }
//        });


//        Observable.concat(integerObservable, stringObservable).subscribe(new Consumer<Serializable>() {
//            @Override
//            public void accept(Serializable serializable) throws Exception {
//                Log.w("RX2", "·····················accept：" + serializable);
//            }
//        });

//        Observable.just(1, 2, 3, 4, 5,6,7,8,9,10).buffer(4, 2).subscribe(new Consumer<List<Integer>>() {
//            @Override
//            public void accept(List<Integer> integers) throws Exception {
//                StringBuffer stringBuffer = new StringBuffer();
//                for (int i = 0; i < integers.size(); i++) {
//                    stringBuffer.append(integers.get(i) + "  ");
//                }
//                Log.w("RX2", "·····················accept："+stringBuffer.toString());
//            }
//        });

//        Single.just(10).subscribe(new SingleObserver<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(@NonNull Integer integer) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//        });

//        Observable.just(1, 2, 3, 4, 5, 6).scan(new BiFunction<Integer, Integer, Integer>() {
//            @Override
//            public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
//                return integer+integer2;
//            }
//        }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.w("RX2", "·····················accept："+integer);
//            }
//        });

        Observable.just(1, 2, 3, 4, 5, 6, 7).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                Log.w("RX2", "·····················map:" + Thread.currentThread());
                return "this is " + integer;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.w("RX2", "·····················doOnNext：" + Thread.currentThread());
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.w("RX2", "·····················onSubscribe：" + Thread.currentThread());
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.w("RX2", "·····················onNext：" + Thread.currentThread());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.w("RX2", "·····················onError：" + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        Log.w("RX2", "·····················onComplete：" + Thread.currentThread());
                    }
                });


        Observable.just("")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(@NonNull String s) throws Exception {
                        Log.w("RX2", "·····················map:" + Thread.currentThread());
                        if (!TextUtils.isEmpty(s)) {
                            return -1;
                        } else {
                            try {
                                return Integer.parseInt(s);
                            } catch (Exception e) {
                                return -2;
                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.w("RX2", "·····················onSubscribe：" + Thread.currentThread());
                    }

                    @Override
                    public void onNext(@NonNull Integer i) {
                        Log.w("RX2", "·····················onNext：" + Thread.currentThread());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.w("RX2", "·····················onError：" + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        Log.w("RX2", "·····················onComplete：" + Thread.currentThread());
                    }
                });
    }

}
