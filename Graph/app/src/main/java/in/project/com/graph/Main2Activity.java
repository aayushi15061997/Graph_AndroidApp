package in.project.com.graph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private EditText n,x,y,start1,end1;
    private Button edges,answer;
    private TextView output;
    ArrayList<Integer> from=new ArrayList<>();
    ArrayList<Integer> to=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        n= (EditText) findViewById(R.id.n);
        x= (EditText) findViewById(R.id.x);
        y= (EditText) findViewById(R.id.y);
        edges= (Button) findViewById(R.id.singleedge);
        answer= (Button) findViewById(R.id.simulate);
        output= (TextView) findViewById(R.id.output);
        start1= (EditText) findViewById(R.id.start);
        end1= (EditText) findViewById(R.id.end);


        edges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Edge added",Toast.LENGTH_SHORT).show();
                from.add(Integer.parseInt(x.getText().toString()));
                to.add(Integer.parseInt(y.getText().toString()));
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Main m=new Main(Integer.parseInt(n.getText().toString()),from,to);
                int start=Integer.parseInt(start1.getText().toString());
                int end=Integer.parseInt(end1.getText().toString());

                boolean k=m.execute(start,end);


                if(k==true)
                    output.setText("Yes");
                else
                    output.setText("No");
            }
        });







    }

}
