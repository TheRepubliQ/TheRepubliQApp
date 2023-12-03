package br.edu.ifsp.tcc.apprepublic.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.view.InfoResidences;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {
    private Context context;
    private List<HomeEntity> homeList;

    public HomePageAdapter(Context context, List<HomeEntity> homeList) {
        this.context = context;
        this.homeList = homeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeEntity home = homeList.get(position);

        holder.textTitleNome.setText(home.getTitulo());
        holder.textPreco.setText(String.valueOf(home.getPreco()));


        holder.btnDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, InfoResidences.class);
                intent.putExtra("home_id", home.getId()); // Envie o ID da casa ou qualquer outra informação necessária
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeList != null ? homeList.size() : 0;
    }

    public void setHomeList(List<HomeEntity> homeList) {
        this.homeList = homeList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitleNome;
        TextView textPreco;

        Button btnDados;

        public ViewHolder(View itemView) {
            super(itemView);
            textTitleNome = itemView.findViewById(R.id.text_title_nome);
            textPreco = itemView.findViewById(R.id.text_preco);
            btnDados = itemView.findViewById(R.id.btn_visualiza);
        }
    }
}
