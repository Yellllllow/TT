package com.taiping.app.util;


import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.taiping.app.cryptoUtil.AesAndRsaMixtureCryptoUtil;
import com.taiping.app.R;
import com.taiping.app.api.remote.ApiFactory;
import com.taiping.app.base.BaseApplication;
import com.taiping.app.model.response.fir.Version;
import com.taiping.app.router.Router;
import com.taiping.app.view.dialog.DialogHelper;
import com.taiping.app.view.dialog.WaitDialog;

import java.lang.ref.WeakReference;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateManager {

    private static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApgmxgkwuBOEcZveYv0POVZoZJSWtNqNU" + "\r"+
            "MrJnvaMb7IzfcKL59rh2DkrHwwdWVNkeNC+zU1BoJsN87F0YCaVrpdhbJeZFT3loO3T2MIz3h0HU" + "\r"+
            "DjJxaAkqQNdVi+JfmRk8dztPhdTYEPxy3vzDm/AdAmkGuSeom3154mzlnxtN2vpCX34WhVSJQHv7" + "\r"+
            "KpMYhnKzoMbGS9zFTTvltcsvWqgKqVmmfkS4812EJueJh6Po51O/N00tlZTaXzyLCOPgLpN5ucjl" + "\r"+
            "bgHgnnz0H/NR8oU/gVMBHAZhFYeonQJs7TahgsMZfn9TbhvP4KjxDyB7CsTlH1f5BM2Nb0FDYO+p" + "\r"+
            "ifdqqwIDAQAB" + "\r";

    private static String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCmCbGCTC4E4Rxm95i/Q85Vmhkl" + "\r"+
            "Ja02o1Qysme9oxvsjN9wovn2uHYOSsfDB1ZU2R40L7NTUGgmw3zsXRgJpWul2Fsl5kVPeWg7dPYw" + "\r"+
            "jPeHQdQOMnFoCSpA11WL4l+ZGTx3O0+F1NgQ/HLe/MOb8B0CaQa5J6ibfXnibOWfG03a+kJffhaF" + "\r"+
            "VIlAe/sqkxiGcrOgxsZL3MVNO+W1yy9aqAqpWaZ+RLjzXYQm54mHo+jnU783TS2VlNpfPIsI4+Au" + "\r"+
            "k3m5yOVuAeCefPQf81HyhT+BUwEcBmEVh6idAmztNqGCwxl+f1NuG8/gqPEPIHsKxOUfV/kEzY1v" + "\r"+
            "QUNg76mJ92qrAgMBAAECggEAP6yl+28Vkt91kfNQC8GIJoHjNMC/LZ3zU0Hh0PL1aYEYy0xlCf3x" + "\r"+
            "oEeDVkLBE+bz01Wbss66Mmtzw/1rk39xyfcv9VfaqsDHfU28gB//aUqQdSVZImmpX5Z1AyE8Qi1Q" + "\r"+
            "Z/VB6PQYLGNz9hn/NhUeOudmIamY2pCDJpdWc+73q4sBkaOL2+W3E0cZT75vzoZQ90S7h6sfkX0G" + "\r"+
            "Erjw/9nUBJBy8+V1w7BSoK4+fcvrLqo8ffVo0PSYVNC57c/jd3+hogo7iKorvTsqwm4NMXTsto5x" + "\r"+
            "vXQxjvcfav0znZiN0Cc2wTMROkw47DBC1dZYbGuPO+w6Six5e6x0ZU/qWKDZQQKBgQDXiOsK/Szi" + "\r"+
            "WHfLvx2EDycqRlY1Kii3RTz3kcE5FI9HPa5isjNbUSG/F3wSNoVg/0AdzeWQbSixkZNau94Wur6i" + "\r"+
            "+97KLwD2qq8J6i/noZIv/P8OQvo/vwHYR/IFbyQf6e2CiK3FhhGyws151PQoqirsmyTlJJZAZWLf" + "\r"+
            "iZTZpe1/ywKBgQDFNddTXixeSBOz0Qjm49V1JhurlILa3JaKUol4iDbvYOh0t1CsjUfGjrkunDHX" + "\r"+
            "6P/T5ACOX+PmnzkjHW0ZSPAddx1A9Hy34o+MGON0GX8nj4p4YoalIW1k/jlGFcctkSoFVFg/RKop" + "\r"+
            "QQGz+XNde3Yr0UK7J0EXzlVRnwYmrbSkoQKBgErxrOjRR74cjOsntReqPTAR7P/nfOjdBmn/IHS8" + "\r"+
            "lWVsKSrgU8M43scXX2jl1FL57k1uvpgNnMzBlb9C++JjZM4/TiR3W5pplxuXdrjQEYjmK7nFyEZK" + "\r"+
            "IFYYDiudja4bJR7yb5nzGExUOCZYyd0p7mr/N0EGC8iweETKDhvv+jkpAoGAOR9iUoSZp2mLQ2+N" + "\r"+
            "+4sM3lT+eNGYoZp5hHFp3l7eQrI4Qu6CUKjPnITkwMp/aYHU8GQ/gP3nfnqqSzCP1F4bJv3EnHb0" + "\r"+
            "1TKrz7G52Hw2J5hdTIeFZrlq/XDh2BogymMc39RRh2n1O+PXgXEE6JQFY6XGhX1WTnX2oqDEYFm6" + "\r"+
            "VGECgYAcLBMZQyRUdKL9XYSdgFLm9YZOu/RGJYoXaHHZlacx5qXwcgz/hhxBnMy9UrMfGvdvx3Lj" + "\r"+
            "lJcAveFt+LgEMGsjU/LntN0QEM1cNrQVOl2MwtV2IeUqTLPJDwoFU1ZRPnzD4ehEidllyui6x2Ri" + "\r"+
            "hozmpCbTKFYi/pK6GNJLEOLpMw==" + "\r";


    private WeakReference<Context> mContext;

    private boolean isShow = false;

    private WaitDialog mWaitDialog;
    private Version mVersion;


    public UpdateManager(Context context, boolean isShow) {
        mContext = new WeakReference<>(context);
        this.isShow = isShow;
    }

    public boolean haveNew() {

        boolean haveNew = false;
        int curVersionCode = OSUtil.getVersionCode();

        if ((Integer.parseInt(mVersion.getVersion()) > curVersionCode)) {

            haveNew = true;
        }
        return haveNew;
    }

    public void checkUpdate() {
        if (isShow) {
            showCheckDialog();
        }



        ApiFactory.getFirApi()
                            .getCarInsurance("http://wxtest.life.cntaiping.com/taipingwx/insurance-policy/vehicle-insurance","120112198111300415","1","20")
                    .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<String>() {

                        @Override
                        public void onCompleted() {
                            // finshCheck();
                        }

                        @Override
                        public void onError(Throwable e) {
                            hideCheckDialog();
                            if (isShow) {
                                showFaileDialog();
                            }

                        }

                        @Override
                    public void onNext(String version) {
                        hideCheckDialog();
                        Log.e("TEST", "onCompleted: "+version );
                        //mVersion = version;
                        String data= version;
                        String encrypt = null;
                        String decrypt=null;

                        try {

//                            Main.main();
                            //encrypt = AesAndRsaMixtureCryptoUtil.encrypt(data, PUBLIC_KEY);
                            decrypt = AesAndRsaMixtureCryptoUtil.decrypt(data,PRIVATE_KEY);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("---*******----"+decrypt);

                    }
                });


    }

    private void finshCheck() {
        if (haveNew()) {
            showUpdateInfo();
        } else {
            if (isShow) {
                showLatestDialog();
            }
        }
    }

    private void showCheckDialog() {
        if ((mContext.get() == null))
            return;
        if (mWaitDialog == null) {
            mWaitDialog = DialogHelper.getWaitDialog(mContext.get(),
                    BaseApplication.resources().getString(R.string.update_checking));
        }
        mWaitDialog.show();
    }

    private void hideCheckDialog() {
        if (mWaitDialog != null) {
            mWaitDialog.dismiss();
        }
    }

    private void showUpdateInfo() {
        if (mVersion == null || mContext.get() == null) {
            return;
            //
        }


        DialogHelper.getConfirmDialog(mContext.get(), mVersion.getName(), mVersion.getChangelog(), BaseApplication.resources().getString(R.string.update_now), BaseApplication.resources().getString(R.string.update_delay), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Router.startDownloadService(mContext.get(), mVersion.getInstallUrl(), BaseApplication.resources().getString(R.string.update_notice));
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setCancelable(false).show();


    }

    private void showLatestDialog() {
        if (mContext.get() == null) return;
        DialogHelper.getMessageDialog(mContext.get(), BaseApplication.resources().getString(R.string.update_no_new)).show();
    }

    private void showFaileDialog() {
        if (mContext.get() == null)
            DialogHelper.getMessageDialog(mContext.get(), BaseApplication.resources().getString(R.string.update_check_error)).show();
    }


}
