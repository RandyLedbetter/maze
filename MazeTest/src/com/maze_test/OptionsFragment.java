package com.maze_test;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class OptionsFragment extends DialogFragment {
	ArrayList selectedItems;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		selectedItems = new ArrayList();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.options_menu).setMultiChoiceItems(R.array.options, null, new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which,
                    boolean isChecked) {
                if (isChecked) {
                    // If the user checked the item, add it to the selected items
                    selectedItems.add(which);
                } else if (selectedItems.contains(which)) {
                    // Else, if the item is already in the array, remove it 
                    selectedItems.remove(Integer.valueOf(which));
                }
            }
		})    // Set the action buttons
        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK, so save the mSelectedItems results somewhere
                // or return them to the component that opened the dialog
            }
        })
        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });;
		
		
		
		
		return builder.create();
	}

}
