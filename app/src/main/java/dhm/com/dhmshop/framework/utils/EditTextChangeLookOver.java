package dhm.com.dhmshop.fromwork.utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @describe 使用EditText监听时只重新某一个 使用的 监听
 */
public interface EditTextChangeLookOver extends TextWatcher {
    @Override
    void onTextChanged(CharSequence charSequence, int i, int i1, int i2);

    @Override
    default void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    default void afterTextChanged(Editable editable) {

    }
}
