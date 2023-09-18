package br.edu.ifsp.tcc.apprepublic.model.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDate;

import br.edu.ifsp.tcc.apprepublic.model.user.Gender;
import br.edu.ifsp.tcc.apprepublic.model.user.User;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "TheRepubliQ.db";
    private static final int DATABASE_VERSION = 1;

    // Defina o nome da tabela

    private static final String TABLE_NAME_USER = "user"; // Tabela de usuário
    private static final String TABLE_NAME_HOME = "home"; // Tabela home

    // Defina as colunas da tabela User
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_TELEFONE = "telefone";
    private static final String COLUMN_NASCIMENTO = "nascimento";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_CPF = "CPF";
    private static final String COLUMN_PROP = "prop";

    // Colunas da tabela home
    private static final String COLUMN_ID_HOME = "id";
    private static final String COLUMN_DESCR = "descr";
    private static final String COLUMN_TIPO = "tipo";
    private static final String COLUMN_PRECO = "preco";
    private static final String COLUMN_OFERTADO = "ofertado";
    private static final String COLUMN_PAIS = "pais";
    private static final String COLUMN_ESTADO = "estado";
    private static final String COLUMN_NUMERO = "numero";
    private static final String COLUMN_RUA = "rua";
    private static final String COLUMN_BAIRRO = "bairro";
    private static final String COLUMN_CIDADE = "cidade";
    private static final String COLUMN_CEP = "cep";
    private static final String COLUMN_COMPLEMENTO = "complemento";




    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)  {
        // Crie a tabela user
        String createUserTableQuery = "CREATE TABLE " + TABLE_NAME_USER + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_EMAIL + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_TELEFONE + " TEXT NOT NULL, " +
                COLUMN_NASCIMENTO + " TEXT NOT NULL, " +
                COLUMN_GENDER + " TEXT NOT NULL, " +
                COLUMN_LOGIN + " TEXT NOT NULL, " +
                COLUMN_CPF + " TEXT NOT NULL, " +
                COLUMN_PROP + " INTEGER NOT NULL);";


        // Crie a tabela home
        String createHomeTableQuery = "CREATE TABLE " + TABLE_NAME_HOME + " (" +
                COLUMN_ID_HOME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DESCR + " TEXT NOT NULL, " +
                COLUMN_TIPO + " TEXT NOT NULL, " +
                COLUMN_PRECO + " REAL NOT NULL, " +
                COLUMN_OFERTADO + " INTEGER NOT NULL, " +
                COLUMN_PAIS + " TEXT NOT NULL, " +
                COLUMN_ESTADO + " TEXT NOT NULL, " +
                COLUMN_NUMERO + " INTEGER NOT NULL, " +
                COLUMN_RUA + " TEXT NOT NULL, " +
                COLUMN_BAIRRO + " TEXT NOT NULL, " +
                COLUMN_CIDADE + " TEXT NOT NULL, " +
                COLUMN_CEP + " VARCHAR(10) NOT NULL, " +
                COLUMN_COMPLEMENTO + " TEXT);";

        db.execSQL(createUserTableQuery);
        db.execSQL(createHomeTableQuery);

        insertTestData(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HOME);
        onCreate(db);
    }

    private void insertTestData(SQLiteDatabase sqLiteDatabase) {
        // Inserções de teste
        String insertQuery1 = "INSERT INTO " + TABLE_NAME_USER + " (" +
                COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " +
                COLUMN_TELEFONE + ", " + COLUMN_NASCIMENTO + ", " + COLUMN_GENDER + ", " +
                COLUMN_LOGIN + ", " + COLUMN_CPF + ", " + COLUMN_PROP + ") VALUES " +
                "('Usuário 1', 'usuario1@example.com', 'senha123', '123456789', '1990-01-01', 'MASCULINO', 'user1', '12345678909', 1);";

        String insertQuery2 = "INSERT INTO " + TABLE_NAME_USER + " (" +
                COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " +
                COLUMN_TELEFONE + ", " + COLUMN_NASCIMENTO + ", " + COLUMN_GENDER + ", " +
                COLUMN_LOGIN + ", " + COLUMN_CPF + ", " + COLUMN_PROP + ") VALUES " +
                "('Usuário 2', 'usuario2@example.com', 'senha456', '987654321', '1985-02-15', 'FEMININO', 'user2', '98765432109', 0);";

        String insertQuery3 = "INSERT INTO " + TABLE_NAME_USER + " (" +
                COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " +
                COLUMN_TELEFONE + ", " + COLUMN_NASCIMENTO + ", " + COLUMN_GENDER + ", " +
                COLUMN_LOGIN + ", " + COLUMN_CPF + ", " + COLUMN_PROP + ") VALUES " +
                "('Usuário 3', 'usuario3@example.com', 'senha789', '555555555', '1995-05-20', 'FEMININO', 'user3', '55555555509', 1);";

        // Inserções de teste para a tabela home
        String insertHomeQuery1 = "INSERT INTO " + TABLE_NAME_HOME + " (" +
                COLUMN_DESCR + ", " + COLUMN_TIPO + ", " + COLUMN_PRECO + ", " +
                COLUMN_OFERTADO + ", " + COLUMN_PAIS + ", " + COLUMN_ESTADO + ", " +
                COLUMN_NUMERO + ", " + COLUMN_RUA + ", " + COLUMN_BAIRRO + ", " +
                COLUMN_CIDADE + ", " + COLUMN_CEP + ", " + COLUMN_COMPLEMENTO + ") VALUES " +
                "('Casa 1', 'CASA', 100000.0, 1, 'Brasil', 'São Paulo', '123', 'Rua 1', 'Bairro A', 'Cidade A', '12345-678', 'Complemento A');";

        // Inserções de teste adicionais para a tabela home
        String insertHomeQuery2 = "INSERT INTO " + TABLE_NAME_HOME + " (" +
                COLUMN_DESCR + ", " + COLUMN_TIPO + ", " + COLUMN_PRECO + ", " +
                COLUMN_OFERTADO + ", " + COLUMN_PAIS + ", " + COLUMN_ESTADO + ", " +
                COLUMN_NUMERO + ", " + COLUMN_RUA + ", " + COLUMN_BAIRRO + ", " +
                COLUMN_CIDADE + ", " + COLUMN_CEP + ", " + COLUMN_COMPLEMENTO + ") VALUES " +
                "('Casa 2', 'CASA', 120000.0, 0, 'Brasil', 'São Paulo', '456', 'Rua 2', 'Bairro B', 'Cidade B', '98765-432', 'Complemento B');";

        String insertHomeQuery3 = "INSERT INTO " + TABLE_NAME_HOME + " (" +
                COLUMN_DESCR + ", " + COLUMN_TIPO + ", " + COLUMN_PRECO + ", " +
                COLUMN_OFERTADO + ", " + COLUMN_PAIS + ", " + COLUMN_ESTADO + ", " +
                COLUMN_NUMERO + ", " + COLUMN_RUA + ", " + COLUMN_BAIRRO + ", " +
                COLUMN_CIDADE + ", " + COLUMN_CEP + ", " + COLUMN_COMPLEMENTO + ") VALUES " +
                "('Apartamento 1', 'REPUBLICA', 80000.0, 1, 'Brasil', 'Rio de Janeiro', '789', 'Rua 3', 'Bairro C', 'Cidade C', '54321-876', 'Complemento C');";

// Execute as inserções de teste adicionais para a tabela home
        // Execute as inserções de teste
        sqLiteDatabase.execSQL(insertQuery1);
        sqLiteDatabase.execSQL(insertQuery2);
        sqLiteDatabase.execSQL(insertQuery3);
        sqLiteDatabase.execSQL(insertHomeQuery1);
        sqLiteDatabase.execSQL(insertHomeQuery2);
        sqLiteDatabase.execSQL(insertHomeQuery3);



    }

    public User validarLogin(String login, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        User usuarioLinha = null;

        String query = "SELECT * FROM " + TABLE_NAME_USER +
                " WHERE " + COLUMN_LOGIN + " = ? AND " + COLUMN_PASSWORD + " = ?";

        String[] selectionArgs = {login, senha};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                usuarioLinha = new User();
                usuarioLinha.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                usuarioLinha.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                usuarioLinha.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
                usuarioLinha.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)));
                usuarioLinha.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONE)));
                usuarioLinha.setCpf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CPF)));
                usuarioLinha.setGender(Gender.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER))));
                usuarioLinha.setIsProp(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PROP)) == 1);
                usuarioLinha.setDataNascimento(LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NASCIMENTO))));
                usuarioLinha.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOGIN)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }

            db.close();
        }

        return usuarioLinha;
    }

}
