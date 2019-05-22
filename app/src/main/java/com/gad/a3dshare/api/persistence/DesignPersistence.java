package com.gad.a3dshare.api.persistence;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gad.a3dshare.AddDesignActivity;
import com.gad.a3dshare.api.model.DesignModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DesignPersistence {
    public static final String TAG = DesignPersistence.class.getSimpleName();

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();

    private String imageUriString;
    private String stlUriString;

    public boolean saveDesign(DesignModel designModel) {
        imageUriString = saveImage(designModel.getImageUri()).toString();
        stlUriString = saveStl(designModel.getStlUri()).toString();

        Log.i(TAG, "Download Uri: " + imageUriString);
        Log.i(TAG, "Download Uri: " + stlUriString);

        return false;
    }

    private Uri saveImage(final Uri imageUri) {
        final StorageReference ref = storageRef.child("image/");
        UploadTask uploadTask = ref.putFile(imageUri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL

                return ref.getDownloadUrl();

            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                } else {
                    // Handle failures

                }
            }
        });

        return urlTask.getResult();
    }

    private Uri saveStl(final Uri stlUri) {
        final StorageReference ref = storageRef.child("stl/");
        UploadTask uploadTask = ref.putFile(stlUri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL

                return ref.getDownloadUrl();

            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                } else {
                    // Handle failures

                }
            }
        });

        return urlTask.getResult();
    }
}
