package in.project.com.graph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView titleTextView, numVerticesTextView, edgesTextView,junk;
    private Button playButton,checkButton;
    private SeekBar verticesSeekbar;
    private ListView edgesListView;
    private List<Model> possibleEdges;
    private int numOfNodes;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        playButton = (Button) findViewById(R.id.button);
        checkButton=(Button) findViewById(R.id.playButton);
        junk=(TextView)findViewById(R.id.textView2);

        numVerticesTextView = (TextView) findViewById(R.id.numVerticesTextView);
        verticesSeekbar = (SeekBar) findViewById(R.id.verticesSeekbar);
        edgesTextView = (TextView) findViewById(R.id.edgesTextView);
        edgesListView = (ListView) findViewById(R.id.edgesListView);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButton.setVisibility(View.GONE);
                titleTextView.setVisibility(View.GONE);
                junk.setVisibility(View.GONE);
                checkButton.setVisibility(View.GONE);

                numVerticesTextView.setVisibility(View.VISIBLE);
                verticesSeekbar.setVisibility(View.VISIBLE);
                edgesTextView.setVisibility(View.VISIBLE);
                edgesListView.setVisibility(View.VISIBLE);

                verticesSeekbar.setMax(10);
                verticesSeekbar.setProgress(0);

                possibleEdges = new ArrayList<>();
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });

        verticesSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numOfNodes = progress;
                possibleEdges = new ArrayList<>();
                for (int i = 1; i <= progress; i++) {
                    for (int j = i + 1; j <= progress; j++) {
                        possibleEdges.add(new Model(i + " -> " + j, i, j, false));
                    }
                }
                CustomAdapter adapter = new CustomAdapter(MainActivity.this, R.layout.row, possibleEdges);
                edgesListView.setAdapter(adapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.simulate) {
            simulateGraph();
        }
        return true;
    }

    private void simulateGraph() {
        if (numOfNodes <= 1) {
            Toast.makeText(this, "Select number of vertices greater than 1", Toast.LENGTH_LONG).show();
        }
        else {
            Graph graph = new Graph(numOfNodes);
            for (int i = 0; i < possibleEdges.size(); i++) {
                if (possibleEdges.get(i).isChecked()) {
                    graph.addEdge(possibleEdges.get(i).getFrom(), possibleEdges.get(i).getTo());
                }
            }
            graph.BFS(1);
            if (graph.isConnected()) {
                int numOfArticulationPoints = graph.articulationPoints();
                int numOfBridges = graph.bridges();
                result = numOfArticulationPoints;
            }
            else {
                result = numOfNodes;
            }

            titleTextView.setText("Your score is " + result);
            playButton.setText("Play Again");

            playButton.setVisibility(View.VISIBLE);
            titleTextView.setVisibility(View.VISIBLE);

            numVerticesTextView.setVisibility(View.GONE);
            verticesSeekbar.setVisibility(View.GONE);
            edgesTextView.setVisibility(View.GONE);
            edgesListView.setVisibility(View.GONE);
        }

    }
}