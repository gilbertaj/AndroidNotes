package gilbertaj.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class NoteListAdapter extends BaseAdapter {

    private Context context;
    private List<String> itemsList;

    /**
     * Class is used to hold the access variables and data for each list item's view.
     */
    private class NoteViewHolder {
        TextView itemNoteName;
        LinearLayout buttonLayout;
        Button renameButton;
        Button deleteButton;
        int position;
    }

    /**
     * Create a NoteListAdapter Instance.
     *
     * This is used to set the Note Name for each list item and to handle click events.
     * Long click events show the rename and delete buttons where as click events open an activity
     * to view the specific note's contents.
     * @param context "Context for the created views"
     * @param itemsList "List of note names for the adapter to assign to each list item"
     */
    NoteListAdapter(Context context, List<String> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public String getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create view from layout using view holder pattern
        NoteViewHolder viewHolder;

        //Checks if the view has been set before. If not then inflate it, set its tags, and set its
        // listeners. If it has been set before then grab its tags.
        if(convertView == null) {
            //Inflate the view
            convertView = View.inflate(context, R.layout.note_list_item, null);

            //Set the view's tags
            viewHolder = new NoteViewHolder();
            viewHolder.itemNoteName = convertView.findViewById(R.id.noteListItemNoteName);
            viewHolder.buttonLayout = convertView.findViewById(R.id.noteListItemButtonLayout);
            viewHolder.renameButton = convertView.findViewById(R.id.noteListItemRenameButton);
            viewHolder.deleteButton = convertView.findViewById(R.id.noteListItemDeleteButton);
            viewHolder.position = position;
            convertView.setTag(viewHolder);

            //Set the various listeners for the list item
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClick(v);
                }
            });

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return itemOnLongClick(v);
                }
            });

            viewHolder.renameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    renameButtonOnClick(v);
                }
            });

            viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteButtonOnClick(v);
                }
            });
        } else {
            //Get the tags from the view
            viewHolder = (NoteViewHolder) convertView.getTag();
        }


        //Set list item's text to this note's name
        viewHolder.itemNoteName.setText(getItem(position));
        viewHolder.buttonLayout.setVisibility(View.GONE);

        return convertView;
    }

    /**
     * Handles the list item's onClick event. When the item item is clicked this hides the list
     * items's buttons and starts a new activity to view the contents of the selected note
     * @param view "The specific list item's view"
     */
    private void itemOnClick(View view) {
        NoteViewHolder viewHolder = (NoteViewHolder) view.getTag();
        String noteName = getItem(viewHolder.position);

        viewHolder.buttonLayout.setVisibility(View.GONE);
    }

    /**
     * Handles the list item's onLongClick event. When the list item is long clicked it shows or
     * hides it's buttons.
     * @param view "The specific list item's view"
     * @return boolean - "Whether or not to consume the event"
     */
    private Boolean itemOnLongClick(View view) {
        NoteViewHolder viewHolder = (NoteViewHolder) view.getTag();

        if(viewHolder.buttonLayout.getVisibility() == View.GONE) {
            viewHolder.buttonLayout.setVisibility(View.VISIBLE);
        } else {
            viewHolder.buttonLayout.setVisibility(View.GONE);
        }

        return true;
    }

    /**
     * Handles the list item's rename button's onClick event. When the button is clicked it prompts
     * the user for a new name for the note and then updates the note's name.
     * @param view "The specific list item's view"
     */
    private void renameButtonOnClick(View view) {

    }

    /**
     * Handles the list item's delete button's onClick event. When the delete button is clicked it
     * prompts the user to confirm the deletion and then deletes the note.
     * @param view "The specific list item's view"
     */
    private void deleteButtonOnClick(View view) {

    }

}
