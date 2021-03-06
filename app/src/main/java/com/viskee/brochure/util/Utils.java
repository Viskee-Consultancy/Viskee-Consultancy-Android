package com.viskee.brochure.util;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.viskee.brochure.BuildConfig;
import com.viskee.brochure.R;
import com.viskee.brochure.adapter.SchoolCourseItemAdapter;
import com.viskee.brochure.model.AIBTSchoolNameEnum;
import com.viskee.brochure.model.Course;
import com.viskee.brochure.model.Department;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static String getJsonFromStorage(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = new FileInputStream(new File(context.getFilesDir() + "/" + fileName));

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.e(Utils.class.getSimpleName(), e.getMessage());
            return null;
        }

        return jsonString;
    }

    public static void setListViewHeightBasedOnChildren(Department department, ListView listView) {
        SchoolCourseItemAdapter listAdapter = (SchoolCourseItemAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int orientation = listView.getResources().getConfiguration().orientation;
        for (int i = 0; i < department.getCourses().size(); i++) {

            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            Course course = department.getCourses().get(i);
            String name = course.getName();

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                totalHeight += listItem.getMeasuredHeight() * (name.length() / 40 + 1);
            } else {
                totalHeight += listItem.getMeasuredHeight() * (name.length() / 100 + 1);
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static Drawable getSchoolLogoDrawable(Context context, String schoolName, int orientation) {
        AIBTSchoolNameEnum aibtSchoolNameEnum = AIBTSchoolNameEnum.fromValue(schoolName.toUpperCase());
        Drawable drawable = null;
        switch (aibtSchoolNameEnum) {
            case ACE:
                drawable = ContextCompat.getDrawable(context, R.drawable.ace_landscape);
                break;
            case BESPOKE:
                drawable = ContextCompat.getDrawable(context, R.drawable.bespoke);
                break;
            case BRANSON:
                drawable = ContextCompat.getDrawable(context, R.drawable.branson);
                break;
            case DIANA:
                drawable = ContextCompat.getDrawable(context, R.drawable.diana);
                break;
            case EDISON:
                drawable = ContextCompat.getDrawable(context, R.drawable.edison);
                break;
            case SHELDON:
                drawable = ContextCompat.getDrawable(context, R.drawable.sheldon);
                break;
            case REACH:
                drawable = ContextCompat.getDrawable(context, R.drawable.reach);
                break;
        }
        return drawable;
    }

    @SuppressLint("WrongConstant")
    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null) {
                return false;
            }
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities == null) {
                return false;
            }
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true;
            }
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo.isConnected();
        }
        return false;
    }

    public static void openPdfFile(Context context, String fileName) {
        File pdfFile =
                new File(context.getFilesDir() + "/" + context.getString(R.string.BROCHURE_DIRECTORY) + "/" + fileName);  // -> filename = maven.pdf
        Uri path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            context.startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }
}
