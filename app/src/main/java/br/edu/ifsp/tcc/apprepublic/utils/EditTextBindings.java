package br.edu.ifsp.tcc.apprepublic.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

public class EditTextBindings {

    @BindingAdapter("android:text")
    public static void setText(EditText view, String value) {
        String newValue = Mascara.mask(Mascara.MASCARA_DATA, value);
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static String getText(EditText view) {
        return Mascara.unmask(view.getText().toString());
    }

    @BindingAdapter("android:textAttrChanged")
    public static void setListener(EditText view, final InverseBindingListener listener) {
        if (listener != null) {
            view.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    listener.onChange();
                }
            });
        }
    }
}
