package com.example.recipes.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.recipes.R;
import com.example.recipes.adapters.RestaurantsAdapter;
import com.example.recipes.interfaces.RestaurantOnClick;
import com.example.recipes.models.Receita;
import com.example.recipes.models.Restaurante;
import com.example.recipes.models.Usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.recipes.views.RegisterActivity.NEW_USER;

public class MainActivity extends AppCompatActivity implements RestaurantOnClick {
    private RecyclerView recyclerRestaurant;
    private RestaurantsAdapter adapterRestaurant;
    private List<Restaurante> listaRestaurantes = new ArrayList<>();
    private List<Receita> listaReceitas;

    public static final String RESTAURANT = "restaurante";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerRestaurant = findViewById(R.id.recycler_main);
        adapterRestaurant = new RestaurantsAdapter(listarRestaurantes(), this);
        recyclerRestaurant.setAdapter(adapterRestaurant);
        recyclerRestaurant.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_profile) {
            bundleToProfile();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Restaurante> listarRestaurantes() {
        listaRestaurantes.add(new Restaurante("Sabor com Cor", "Rua Skada, 123 - Orlando, DC", "Fecha às 22h", R.mipmap.restaurantea, listarReceitas()));
        listaRestaurantes.add(new Restaurante("Organico’s", "Rua Skada, 123 - Orlando, DC", "Fecha às 22h", R.mipmap.restauranteb, listarReceitas()));
        listaRestaurantes.add(new Restaurante("Tropical", "Rua Skada, 123 - Orlando, DC", "Fecha às 22h", R.mipmap.restaurantec, listarReceitas()));
        listaRestaurantes.add(new Restaurante("Vita Gastronomia", "Rua Skada, 123 - Orlando, DC", "Fecha às 22h", R.mipmap.restauranted, listarReceitas()));
        listaRestaurantes.add(new Restaurante("Al Natural", "Rua Skada, 123 - Orlando, DC", "Fecha às 22h", R.mipmap.restaurantek, listarReceitas()));

        return listaRestaurantes;
    }

    public List<Receita> listarReceitas() {
        listaReceitas = new ArrayList<>();
        listaReceitas.add(new Receita("Hamburguer A", R.mipmap.hamaa, getString(R.string.large_text)));
        listaReceitas.add(new Receita("Hamburguer A", R.mipmap.hamab, getString(R.string.large_text)));
        listaReceitas.add(new Receita("Hamburguer A", R.mipmap.hamac, getString(R.string.large_text)));
        listaReceitas.add(new Receita("Hamburguer A", R.mipmap.hamad, getString(R.string.large_text)));
        listaReceitas.add(new Receita("Hamburguer A", R.mipmap.hamae, getString(R.string.large_text)));
        listaReceitas.add(new Receita("Hamburguer A", R.mipmap.hamag, getString(R.string.large_text)));
        listaReceitas.add(new Receita("Hamburguer A", R.mipmap.hamah, getString(R.string.large_text)));
        listaReceitas.add(new Receita("Hamburguer A", R.mipmap.hamai, getString(R.string.large_text)));
        listaReceitas.add(new Receita("Hamburguer A", R.mipmap.hamak, getString(R.string.large_text)));

        return listaReceitas;
    }

    @Override
    public void onClick(Restaurante restaurante) {
        Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESTAURANT, restaurante);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void bundleToProfile() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Usuario usuario = getIntent().getExtras().getParcelable(NEW_USER);
            Intent newIntent = new Intent(MainActivity.this, ProfileActivity.class);
            Bundle newBundle = new Bundle();
            newBundle.putParcelable(NEW_USER, usuario);
            newIntent.putExtras(newBundle);
            startActivity(newIntent);
        }
    }
}
