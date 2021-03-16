package com.example.baseadapteradd;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Генератор случайностей
    private Random random = new Random();
    // Наш адаптер
    private ItemsDataAdapter adapter;
    // Список картинок, которые мы будем брать для нашего списка
    private List<Drawable> images = new ArrayList<>();
    private List<String> Murphy = new ArrayList<>();
    private SaveExtFile saveExtFile = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        ListView listView = findViewById(R.id.listView);

        setSupportActionBar(toolbar);

        fillImages();

        // При тапе по кнопке добавим один новый элемент списка
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomItemData();
                saveExtFile.saveStringList(adapter.getAdapterStrings());
            }
        });
// Создаем и устанавливаем адаптер на наш Файл getString(R.string.save_file
        saveExtFile = new SaveExtFile(MainActivity.this, "save_file");
        adapter = new ItemsDataAdapter(this, null, saveExtFile);
        listView.setAdapter(adapter);

        generateLoadedItemData();


        // При тапе по элементу списка будем показывать его данные

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showItemData(position);
                return true;
            }
        });


    }

    // Заполним различными картинками, которые встроены в сам Android
    // ContextCompat обеспечит нам поддержку старых версий Android
    private void fillImages() {
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic001));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic002));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic003));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic004));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic005));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic006));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic007));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic008));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic009));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic010));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic011));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic012));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic013));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic014));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.ic015));

        Murphy = Arrays.asList(getResources().getStringArray(R.array.Murphy));
    }

    // Создадим ну почти случайные данные для нашего списка.
    // random.nextInt(граница_последнего_элемента)
    // Для каждого элемента мы возьмем 1 случайную картинку
    // из 5, которые мы сделали вначале.
    private void generateRandomItemData() {
        adapter.addItem(new ItemData(
                images.get(random.nextInt(images.size())),
                Murphy.get(random.nextInt(Murphy.size())),
                "Закон Мерфи..."
        ));
    }

    private void generateLoadedItemData() {
        List<String> list = saveExtFile.loadStringList();
        if (list == null) return;
        for (String s : list) {
            adapter.addItem(new ItemData(
                    images.get(random.nextInt(images.size())),
                    s,
                    "Закон Мерфи..."));
        }

    }

    // Покажем сообщение с данными
    private void showItemData(int position) {
        ItemData itemData = adapter.getItem(position);
        Toast.makeText(MainActivity.this,
                "Title: " + itemData.getTitle() + "\n" +
                        "Length: " + itemData.getSubtitle().length() + "\n"
                ,
                Toast.LENGTH_SHORT).show();
    }
}