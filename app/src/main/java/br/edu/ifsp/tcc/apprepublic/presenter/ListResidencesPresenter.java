package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ifsp.tcc.apprepublic.mvp.ListResidencesMVP;
import br.edu.ifsp.tcc.apprepublic.view.RegisterResidence;

public class ListResidencesPresenter implements ListResidencesMVP.Presenter {
    private ListResidencesMVP.View view;
    private Context context;


    public ListResidencesPresenter(ListResidencesMVP.View view, Context context) {
        this.view = view;
        this.context = context;

    }

    @Override
    public void adcionarResidencia() {
        Intent intent = new Intent(context, RegisterResidence.class);
        context.startActivity(intent);
    }
}
