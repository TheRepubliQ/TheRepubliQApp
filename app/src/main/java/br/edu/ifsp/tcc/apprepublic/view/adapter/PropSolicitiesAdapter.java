package br.edu.ifsp.tcc.apprepublic.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.PropSolicitesMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.PropSolicitiesPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class PropSolicitiesAdapter extends RecyclerView.Adapter<PropSolicitiesAdapter.ViewHolder> {

    private List<HomeEntity> propSolicitiesList; // Substitua o tipo da lista pelo tipo real dos dados
    private Context context;
    private PropSolicitiesPresenter presenter;

    public PropSolicitiesAdapter(Context context, List<HomeEntity> propSolicitiesList) {
        this.context = context;
        this.propSolicitiesList = propSolicitiesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View propSolicityView = inflater.inflate(R.layout.list_view_residences_prop_solicities, parent, false);

        return new ViewHolder(propSolicityView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeEntity propSolicity = propSolicitiesList.get(position);

        holder.textViewPropSolicity.setText(propSolicity.getTitulo());
        holder.imageViewPropSolicity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.info(propSolicity);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return propSolicitiesList != null ? propSolicitiesList.size() : 0;
    }

    public void setPropSolicitiesList(List<HomeEntity> propSolicitiesList) {
        this.propSolicitiesList = propSolicitiesList;
    }

    public void setPresenter(PropSolicitiesPresenter presenter) {this.presenter = presenter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPropSolicity;
        public ImageView imageViewPropSolicity;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPropSolicity = itemView.findViewById(R.id.text_title_listitem);
            imageViewPropSolicity = itemView.findViewById(R.id.image_trash);
            // Encontrar outros elementos no layout conforme necessário
        }
    }
}
