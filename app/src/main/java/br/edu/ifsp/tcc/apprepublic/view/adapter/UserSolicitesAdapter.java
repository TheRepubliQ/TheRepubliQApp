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
import br.edu.ifsp.tcc.apprepublic.model.request.Request;
import br.edu.ifsp.tcc.apprepublic.mvp.UserSolicitesMVP;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class UserSolicitesAdapter extends RecyclerView.Adapter<UserSolicitesAdapter.ViewHolder> {

    private  List<HomeEntity> solicitesList; // Substitua o tipo da lista pelo tipo real dos dados
    private  Context context;

    private UserSolicitesMVP.Presenter presenter;


    public UserSolicitesAdapter(Context context, List<HomeEntity> solicitesList) {
        this.context = context;
        this.solicitesList = solicitesList;
    }

    public void setPresenter(UserSolicitesMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userSolicitesView = inflater.inflate(R.layout.list_view_residences_solicities, parent, false);

        return new ViewHolder(userSolicitesView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obter dados com base na posição
        HomeEntity residence = solicitesList.get(position);

        // Configurar os elementos do ViewHolder com os dados
        holder.textViewResidence.setText(residence.getTitulo());

        holder.imageViewDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.contato(residence);
                notifyDataSetChanged();

            }
        });

        holder.imageViewTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.excluir(residence);
                notifyDataSetChanged();
            }
        });

        // Adicione aqui qualquer lógica adicional para configurar outros elementos conforme necessário
    }

    @Override
    public int getItemCount() {
        return solicitesList != null ? solicitesList.size() : 0;
    }

    public void setHomeList(List<HomeEntity> residenceList) {
        this.solicitesList = residenceList;
    }


    // Classe ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewResidence;
        public ImageView imageViewTrash;
        public ImageView imageViewDados;

        public ViewHolder(View itemView) {
            super(itemView);

            // Encontrar os elementos no layout
            textViewResidence = itemView.findViewById(R.id.text_residences_listitem);
            imageViewTrash = itemView.findViewById(R.id.image_trash);
            imageViewDados = itemView.findViewById(R.id.image_dados);
        }
    }
}
