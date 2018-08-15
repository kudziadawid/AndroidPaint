package com.kudziadawid.androidpaint.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kudziadawid.androidpaint.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawingView mDrawingView;
    private ImageButton currPaint, drawButton, pencilButton, eraseButton, newButton, saveButton, undoButton;
    private float smallBrush, mediumBrush, largeBrush, smallPencil, mediumPencil, largePencil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawingView = findViewById(R.id.drawing);
        LinearLayout paintLayout = findViewById(R.id.paint_colors);

        currPaint = (ImageButton) paintLayout.getChildAt(1);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.pallet_pressed));
        drawButton = findViewById(R.id.buttonBrush);
        drawButton.setOnClickListener(this);
        pencilButton = findViewById(R.id.buttonPencil);
        pencilButton.setOnClickListener(this);
        eraseButton =  findViewById(R.id.buttonErase);
        eraseButton.setOnClickListener(this);
        newButton = findViewById(R.id.buttonNew);
        newButton.setOnClickListener(this);
        saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(this);
        undoButton = findViewById(R.id.buttonUndo);
        undoButton.setOnClickListener(this);

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
        smallPencil = 1;
        mediumPencil = 2;
        largePencil = 3;

        mDrawingView.setBrushSize(mediumBrush);

    }

    public void paintClicked(View view){
        if (view != currPaint){

            ImageButton imageButton = (ImageButton) view;
            String colorTag = imageButton.getTag().toString();
            mDrawingView.setColor(colorTag);

            imageButton.setImageDrawable(getResources().getDrawable(R.drawable.pallet_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.pallet));
            currPaint = (ImageButton) view;
            mDrawingView.setErase(false);
            mDrawingView.setBrushSize(mDrawingView.getLastBrushSize());
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.buttonBrush:
                showBrushSizeChooserDialog();
                break;
            case R.id.buttonPencil:
                showPencilSizeChooserDialog();
                break;
            case R.id.buttonErase:
                showEraserSizeChooserDialog();
                break;
            case R.id.buttonNew:
                showNewPaintingAlertDialog();
                break;
            case R.id.buttonSave:
                showSavePaintingConfirmationDialog(this);
                break;
        }
    }

    private void showBrushSizeChooserDialog(){
        final Dialog brushDialog = new Dialog(this);
        brushDialog.setContentView(R.layout.dialog_brush_size);
        brushDialog.setTitle("Brush size:");
        ImageButton smallBtn = brushDialog.findViewById(R.id.small_brush);
        smallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setBrushSize(smallBrush);
                mDrawingView.setLastBrushSize(smallBrush);
                brushDialog.dismiss();
            }
        });
        ImageButton mediumBtn = brushDialog.findViewById(R.id.medium_brush);
        mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setBrushSize(mediumBrush);
                mDrawingView.setLastBrushSize(mediumBrush);
                brushDialog.dismiss();
            }
        });

        ImageButton largeBtn = brushDialog.findViewById(R.id.large_brush);
        largeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDrawingView.setBrushSize(largeBrush);
                mDrawingView.setLastBrushSize(largeBrush);
                brushDialog.dismiss();
            }
        });
        mDrawingView.setErase(false);
        brushDialog.show();
    }

    private void showPencilSizeChooserDialog() {
        final Dialog pencilDialog = new Dialog(this);
        pencilDialog.setContentView(R.layout.dialog_brush_size);
        pencilDialog.setTitle("Pencil size:");
        ImageButton smallBtn = pencilDialog.findViewById(R.id.small_brush);
        smallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setBrushSize(smallPencil);
                mDrawingView.setLastBrushSize(smallPencil);
                pencilDialog.dismiss();
            }
        });
        ImageButton mediumBtn = pencilDialog.findViewById(R.id.medium_brush);
        mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setBrushSize(mediumPencil);
                mDrawingView.setLastBrushSize(mediumPencil);
                pencilDialog.dismiss();
            }
        });

        ImageButton largeBtn = pencilDialog.findViewById(R.id.large_brush);
        largeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDrawingView.setBrushSize(largePencil);
                mDrawingView.setLastBrushSize(largePencil);
                pencilDialog.dismiss();
            }
        });
        mDrawingView.setErase(false);
        pencilDialog.show();
    }

    private void showEraserSizeChooserDialog(){
        final Dialog brushDialog = new Dialog(this);
        brushDialog.setTitle("Eraser size:");
        brushDialog.setContentView(R.layout.dialog_brush_size);
        ImageButton smallBtn = brushDialog.findViewById(R.id.small_brush);
        smallBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDrawingView.setErase(true);
                mDrawingView.setBrushSize(smallBrush);
                brushDialog.dismiss();
            }
        });
        ImageButton mediumBtn = brushDialog.findViewById(R.id.medium_brush);
        mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setErase(true);
                mDrawingView.setBrushSize(mediumBrush);
                brushDialog.dismiss();
            }
        });
        ImageButton largeBtn = brushDialog.findViewById(R.id.large_brush);
        largeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setErase(true);
                mDrawingView.setBrushSize(largeBrush);
                brushDialog.dismiss();
            }
        });
        brushDialog.show();
    }

    private void showNewPaintingAlertDialog(){
        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("New drawing");
        newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
        newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDrawingView.startNew();
                dialog.dismiss();
            }
        });
        newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        newDialog.show();
    }

    private void showSavePaintingConfirmationDialog(final Context context){
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Save drawing");
        saveDialog.setMessage("Save drawing to device Gallery?");
        saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                String path = Environment.getExternalStorageDirectory().toString();
                OutputStream fOut = null;
                File file = new File(path, UUID.randomUUID().toString()+".jpg");
                Log.d("Paint", "file: " + file.toString());
                if(!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    fOut = new FileOutputStream(file, false);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                mDrawingView.setDrawingCacheEnabled(true);
                mDrawingView.buildDrawingCache();
                Log.d("Paint", mDrawingView.getDrawingCache().toString());
                Bitmap pictureBitmap = mDrawingView.getDrawingCache();
                pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                try {
                    fOut.flush();
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                mDrawingView.destroyDrawingCache();
            }
        });
        saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        saveDialog.show();
    }
}
