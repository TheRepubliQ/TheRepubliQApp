package br.edu.ifsp.tcc.apprepublic.view.adapter;

import android.annotation.SuppressLint;
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
import br.edu.ifsp.tcc.apprepublic.mvp.ListResidencesMVP;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class ListResidencesAdapter extends RecyclerView.Adapter<ListResidencesAdapter.ViewHolder> {
    private Context context;
    private List<HomeEntity> residenceList;

    private ListResidencesMVP.Presenter presenter;

    public ListResidencesAdapter(Context context, List<HomeEntity> residenceList) {
        this.context = context;
        this.residenceList = residenceList;
    }

    public void setPresenter(ListResidencesMVP.Presenter presenter) {
        this.presenter = presenter;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_residences, parent, false); // Use o layout fornecido
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeEntity residence = residenceList.get(position);

        holder.textTitle.setText(residence.getDescr());

        if(residence.getOfertado()){
            holder.imageEdit.setColorFilter(context.getColor(R.color.yellow));
        }else{
            holder.imageEdit.setColorFilter(context.getColor(R.color.gray));
        }
        holder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(residence.getOfertado()){
                    desativar(residence);
                }else{
                    ativar(residence);
                }
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void desativar(HomeEntity residence){
        presenter.desativar(residence);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void ativar(HomeEntity residence){
        presenter.ativar(residence);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return residenceList != null ? residenceList.size() : 0;

    }

    public void setHomeList(List<HomeEntity> residenceList) {
        this.residenceList = residenceList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageEdit;
        TextView textTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imageEdit = itemView.findViewById(R.id.image_edit);
            textTitle = itemView.findViewById(R.id.text_title_listitem);
        }
    }
}
