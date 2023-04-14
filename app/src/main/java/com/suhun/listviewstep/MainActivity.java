package com.suhun.listviewstep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button add;
    private EditText userInput;
    private ListView dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListView();
    }
    //Step1 初始化所有的元件(按鈕、輸入框、要呈現資料的ListView)
    void initView(){
        add = findViewById(R.id.addBtn);
        userInput = findViewById(R.id.userInputEditText);
        dataList = findViewById(R.id.dataListView);
    }
    //Step2：因為ListView本身是有資料結構的，所以我們要先初始化
    //(2).使用SimpleAdapter的物件來當作dataList.setAdapter(需要傳入的參數)。
    // 宣告資料型態為SimpleAdapter的變數simpleAdapter
    private SimpleAdapter simpleAdapter;
    //Step 3-2
    private List<HashMap<String, String>> data = new ArrayList<>();
    //Step 5 需要放入的資料型態為String[]，命名為from，該資料陣列放入的東西為字典的Key(可隨意命名)
    private String[] from = {"Suhun"};
    //Step 6 要放入的資料型態為int[]，放入的內容為該資料要放入哪一個元件，我們剛剛有建立一個名稱為item的layout
    //並創立了一個TextView命名為item_rec，所們的資料想在這個item_rec呈現
    private int[] to = {R.id.item_rec};

    void initListView(){
        //(3).在初始化的function在記憶體裡創建資料型態為SimpleAdapter的物件
         //1.Context context : 要在哪裡執行。
         //2.List: 指的是要帶入的資料。
         //3.@LayoutRes int resource : 在這是只要使用的item格式的layout的位置。
         //4.String[] from : 要帶入資料的key。
         //5.@IdRes int[] to : 指的是key的值要帶入哪一個元件。
        simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        //Step 3-1
        //List<? extends Map<String,?>> data
        //1.由上面可知第二個參數我們必須放入資料型態為List的物件，變數名稱也取名為data的參數
        //2.? extends Map<String,?>得知List裡面限定放入資料型態為繼承Map的物件
        //3.<String,?>得知該Map物件放入的資料限定為String,?
        dataList.setAdapter(simpleAdapter);//(1).先使用物件的setAdapter調配器方法，知道裡面必須方法裡必須傳入ListAdapter資料型態的物件
        //Step 4建立resource file 命名為item(layout資料夾按右鍵->New->Layout Resource file ->命名為item
        //第三個參數放入R.layout.item並實作item.xml
    }

    public void addFunc(View view){
        //由 3-1 List<? extends Map<String,?>>可以知道我們必須宣告一個資料型態繼承Map的物件
        //<String,?>得知該Map物件放入的資料限定為String,?
        //Step 1:宣告一個資料型態為HashMap的物件命名為mapData，該物件未來要存放的資料，資料型態為<String, String>
        HashMap<String, String> mapData = new HashMap<>();
        //Step 2:把該筆資料放入Key命稱為我們定義的from[0]也就是Suhun，放入的資料由EditText取出
        mapData.put(from[0], userInput.getText().toString());
        //Step 3:把資料放入Step3-2宣告的private List<HashMap<String, String>> data;裡面
        data.add(mapData);
        //Step4 通知調配器更新元件顯示資料
        simpleAdapter.notifyDataSetChanged();
        userInput.setText("");
    }
}