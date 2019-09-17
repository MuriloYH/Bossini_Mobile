package br.usjt.ciclodevidagpsemapas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListaLocaisActivity extends AppCompatActivity {

    private ListView locaisListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locais);
        Intent origemIntent = getIntent();
        final List<String> locais = origemIntent.getStringArrayListExtra("historyPos");
        locaisListView = findViewById(R.id.locaisListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, locais);
        locaisListView.setAdapter(adapter);
        locaisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String locaisList = locaisListView.getItemAtPosition(position).toString();
                String lat = locaisList.split(",")[0].split(":")[1];
                String lon = locaisList.split(",")[1].split(":")[1];

                Uri gmmIntentUri = Uri.parse(String.format("geo:%s,%s", lat, lon));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

//                Intent intent = new Intent(ListaLocaisActivity.this, DetalhesLocalActivity.class);
//                intent.putExtra("local_escolhido", locais.get(position));
//                startActivity(intent);
            }
        });
        Toast.makeText(this, "Salvo", Toast.LENGTH_LONG).show();
    }

//    public List<String> geraListaLocais() {
//        ArrayList<String> lista = new ArrayList<>();
//        lista.add("Desktops: Computador da secretÃ¡ria quebrado.");
//        lista.add("Telefonia: Telefone nÃ£o funciona.");
//        lista.add("Redes: ManutenÃ§Ã£o no proxy.");
//        lista.add("Servidores: LentidÃ£o generalizada.");
//        lista.add("Novos Projetos: CRM");
//        lista.add("ManutenÃ§Ã£o Sistema ERP: atualizar versÃ£o.");
//        lista.add("Novos Projetos: Rede MPLS");
//        lista.add("ManutenÃ§Ã£o Sistema de Vendas: incluir pipeline.");
//        lista.add("ManutenÃ§Ã£o Sistema ERP: erro contÃ¡bil");
//        lista.add("Novos Projetos: GestÃ£o de OrÃ§amento");
//        lista.add("Novos Projetos: Big Data");
//        lista.add("Manoel de Barros");
//        lista.add("Redes: Internet com lentidÃ£o");
//        lista.add("Novos Projetos: Chatbot");
//        lista.add("Desktops: troca de senha");
//        lista.add("Desktops: falha no Windows");
//        lista.add("Novos Projetos: ITIL V3");
//        lista.add("Telefonia: liberar celular");
//        lista.add("Telefonia: mover ramal");
//        lista.add("Redes: ponto com defeito");
//        lista.add("Novos Projetos: ferramenta EMM");
//        return lista;
//    }
//
//    public List<String> buscaLocais (String chave) {
//        List<String> lista = geraListaLocais();
//        if (chave == null || chave.length() == 0) {
//            return lista;
//        }
//        else {
//            List<String> subLista = new ArrayList<>();
//            for (String search:lista) {
//                if(search.toUpperCase().contains(chave.toUpperCase())) {
//                    subLista.add(search);
//                }
//            }
//            return subLista;
//        }
//    }
}
