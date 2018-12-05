package com.example.pozsikmt.receptkonyv_v2.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pozsikmt.receptkonyv_v2.R;
import com.example.pozsikmt.receptkonyv_v2.data.ReceptItem;

public class NewReceptItemDialogFragment extends DialogFragment {

    public static final String TAG = "NewReceptItemDialogFragment";

    public interface NewReceptItemDialogListener {
        void onReceptItemCreated(ReceptItem newItem);
    }

    private NewReceptItemDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof NewReceptItemDialogListener) {
            listener = (NewReceptItemDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.new_recept_item)
                .setView(getContentView())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isValid()) {
                            listener.onReceptItemCreated(getReceptItem());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private boolean isValid() {
        return nameEditText.getText().length() > 0;
    }

    private ReceptItem getReceptItem() {
        ReceptItem receptItem = new ReceptItem();
        receptItem.name = nameEditText.getText().toString();
        receptItem.description = descriptionEditText.getText().toString();
        receptItem.category = ReceptItem.Category.getByOrdinal(categorySpinner.getSelectedItemPosition());
        receptItem.isFavourite = ReceptItemisFavouriteCheckBox.isChecked();
        return receptItem;
    }

    private EditText nameEditText;
    private EditText descriptionEditText;
    private Spinner categorySpinner;
    private CheckBox ReceptItemisFavouriteCheckBox;

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_recept_item, null);
        nameEditText = contentView.findViewById(R.id.ReceptItemNameEditText);
        descriptionEditText = contentView.findViewById(R.id.ReceptItemDescriptionEditText);
        categorySpinner = contentView.findViewById(R.id.ReceptItemCategorySpinner);
        categorySpinner.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.category_items)));
        ReceptItemisFavouriteCheckBox = contentView.findViewById(R.id.isFavouriteCheckBox);
        return contentView;
    }


}
