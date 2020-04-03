package com.example.AddMultipleControlsDynamically;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText num_et;
    private Button num_btn;
    private LinearLayout titles_ll;
    private TextView shownum_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        num_et=(EditText)findViewById(R.id.num_et);
        num_btn=(Button)findViewById(R.id.num_btn);
        titles_ll=(LinearLayout)findViewById(R.id.titles_ll);
        shownum_tv=(TextView)findViewById(R.id.shownum_tv);
        num_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(num_et.getText().toString())){
                    /**
                     * 这里是判断，不能的行为
                     */
                    Toast.makeText(MainActivity.this, "请先填个数", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(num_et.getText().toString().equals("0")){
                    Toast.makeText(MainActivity.this, "不能填0", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num=Integer.parseInt(num_et.getText().toString());//这里是得到多少个
                titles_ll.removeAllViews();//linearLayout去掉所有余留的item
                for (int i=0;i<num;i++){
                    final LinearLayout ll= (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.combin_item_button,null);//套LL是为了布局排列
                    ll.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));//此处设置权重
                    final Button btn=(Button)ll.findViewById(R.id.top_one);
                    int m=i+1;
                    btn.setText("第"+m+"个按钮");//监听
                    titles_ll.addView(ll);
                }

                for(int j=0;j<titles_ll.getChildCount();j++){
                    final Button bt=(Button) titles_ll.getChildAt(j).findViewById(R.id.top_one);
                    final int finalJ = j;
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for(int m=0;m<titles_ll.getChildCount();m++){
                                titles_ll.getChildAt(m).findViewById(R.id.top_one).setEnabled(true);
                            }
                            bt.setEnabled(false);
                            shownum_tv.setText("您点击了"+bt.getText().toString());
                        }
                    });
                }
                titles_ll.getChildAt(0).findViewById(R.id.top_one).performClick();
                titles_ll.getChildAt(0).findViewById(R.id.top_one).setEnabled(false);
            }
        });
    }


}