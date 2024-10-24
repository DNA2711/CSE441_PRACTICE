package vn.edu.tlu.m2151170579;

import android.os.Bundle;
import vn.edu.tlu.m2151170579.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private List<MenuItem> menuItems;
    private DatabaseHelper dbHelper;  // Thêm biến DatabaseHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        recyclerView = findViewById(R.id.recycler_view);

        menuItems = new ArrayList<>();

        // Khởi tạo adapter trước
        adapter = new MenuAdapter(menuItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Sau đó, tải dữ liệu từ cơ sở dữ liệu
        loadDataFromDatabase();

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            return true;
        });
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadDataFromDatabase() {
        List<Mon> monList = dbHelper.getAllMon();

        for (Mon mon : monList) {
            menuItems.add(new MenuItem(mon.getTenMon(), mon.getDonGia() + " đ"));
        }

        adapter.notifyDataSetChanged();
    }
}
