package com.yanyuanquan.gank.main.presenter;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

import com.yanyuanquan.gank.R;
import com.yanyuanquan.gank.base.BasePresenter;
import com.yanyuanquan.gank.main.model.SubmitGankModel;
import com.yanyuanquan.gank.main.view.ISubmitGankView;
import com.yanyuanquan.gank.util.RegexUtils;


public class SubmitGankPresenter extends BasePresenter<ISubmitGankView> implements SubmitGankModel.OnSubmitListListener {

    private Context context;
    private SubmitGankModel submitGankModel;
    private TextInputEditText etUrl;
    private TextInputEditText etDesc;
    private TextInputEditText etWho;
    private TextInputEditText etType;

    public SubmitGankPresenter(ISubmitGankView view) {
        super(view);
        this.context = view.getContext();
        this.submitGankModel = new SubmitGankModel();
    }

    public void submitGank(String tag, TextInputEditText etUrl, TextInputEditText etDesc, TextInputEditText etWho, TextInputEditText etType) {
        this.etUrl = etUrl;
        this.etDesc = etDesc;
        this.etWho = etWho;
        this.etType = etType;

        if (!validateInput()) return;

        submitGankModel.submitGank(etUrl.getText().toString(), etDesc.getText().toString(), etWho.getText().toString(), etType.getText().toString(), this);

    }

    private boolean validateInput() {
        boolean hasErrors = false;

        if (TextUtils.isEmpty(etUrl.getText())) {
            setError(etUrl, R.string.et_error_no_url);
            hasErrors = true;
        } else {
            removeError(etUrl);
            if (!RegexUtils.checkURL(etUrl.getText().toString())) {
                setError(etUrl, R.string.et_error_no_urls);
                hasErrors = true;
            }
        }

        if (TextUtils.isEmpty(etDesc.getText())) {
            setError(etDesc, R.string.et_error_no_desc);
            hasErrors = true;
        } else {
            removeError(etDesc);
        }

        if (TextUtils.isEmpty(etWho.getText())) {
            setError(etWho, R.string.et_error_no_who);
            hasErrors = true;
        } else {
            removeError(etWho);
        }

        if (TextUtils.isEmpty(etType.getText())) {
            setError(etType, R.string.et_error_no_type);
            hasErrors = true;
        } else {
            removeError(etType);
        }


        return !hasErrors;
    }


    private void setError(TextInputEditText editText, @StringRes int errorRes) {
        TextInputLayout layout = (TextInputLayout) editText.getParent();
        layout.setError(context.getString(errorRes));
    }


    private void removeError(TextInputEditText editText) {
        TextInputLayout layout = (TextInputLayout) editText.getParent();
        layout.setError(null);
    }

    @Override
    public void onSuccess(String msg) {
        etUrl.setText(null);
        etDesc.setText(null);
        etWho.setText(null);
        etType.setText(null);
        mView.showSuccessMsg(msg);
    }

    @Override
    public void onFailure(String msg, Throwable e) {
        mView.showFailMsg(e.getMessage());
    }
}
