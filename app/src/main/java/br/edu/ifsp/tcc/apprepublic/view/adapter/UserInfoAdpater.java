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

import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.UserInfoMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.PropSolicitiesPresenter;
import br.edu.ifsp.tcc.apprepublic.presenter.UserInfoPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class UserInfoAdpater extends RecyclerView.Adapter<UserInfoAdpater.ViewHolder> {

    private List<User> userInfoList; // Substitua o tipo da lista pelo tipo real dos dados
    private Context context;

    private UserInfoPresenter presenter;


    public UserInfoAdpater(Context context, List<User> userInfoList) {
        this.context = context;
        this.userInfoList = userInfoList;
    }

    public void setPresenter(UserInfoPresenter presenter) {this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userInfoView = inflater.inflate(R.layout.list_view_user, parent, false);

        return new ViewHolder(userInfoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User userInfo = userInfoList.get(position);

        holder.textViewUserName.setText(userInfo.getName());

        holder.imageViewDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.info(userInfo);
                notifyDataSetChanged();
            }
        });

        // Adicione aqui a configuração de outros elementos conforme necessário
    }

    @Override
    public int getItemCount() {
        return userInfoList != null ? userInfoList.size() : 0;
    }

    public void setUserInfoList(List<User> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewUserName;
        public ImageView imageViewDados;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.text_nome_listitem);
            imageViewDados = itemView.findViewById(R.id.image_dados);
            // Encontrar outros elementos no layout conforme necessário
        }
    }
}
