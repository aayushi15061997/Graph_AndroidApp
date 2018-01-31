package in.project.com.graph;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    int resource;

    public CustomAdapter(Context context, int resource, List<Model> modelItems) {
        super(context, resource, modelItems);
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout edgeItemView;
        final Model model = (Model) getItem(position);
        if (convertView == null) {
            edgeItemView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(inflater);
            layoutInflater.inflate(resource, edgeItemView, true);
        }
        else {
            edgeItemView = (LinearLayout) convertView;
        }

        TextView textView = (TextView) edgeItemView.findViewById(R.id.textView);
        CheckBox checkBox = (CheckBox) edgeItemView.findViewById(R.id.checkbox);
        textView.setText(model.getEdgeString());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.setChecked(isChecked ? true : false);
            }
        });
        if (model.isChecked()) {
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }
        return edgeItemView;
    }
}