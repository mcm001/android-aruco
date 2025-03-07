package CameraCalibration.calibration;

import CameraCalibration.CameraCalibrationActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import me.ngargi.engr100_vision.MainActivity;
import org.opencv.core.Mat;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

public abstract class CalibrationResult {
    private static final String TAG = "CalibrationResult";

    private static final int CAMERA_MATRIX_ROWS = 3;
    private static final int CAMERA_MATRIX_COLS = 3;
    private static final int DISTORTION_COEFFICIENTS_SIZE = 5;

    public static void save(Activity activity, Mat cameraMatrix, Mat distortionCoefficients) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        double[] cameraMatrixArray = new double[CAMERA_MATRIX_ROWS * CAMERA_MATRIX_COLS];
        cameraMatrix.get(0, 0, cameraMatrixArray);
        for (int i = 0; i < CAMERA_MATRIX_ROWS; i++) {
            for (int j = 0; j < CAMERA_MATRIX_COLS; j++) {
                Integer id = i * CAMERA_MATRIX_ROWS + j;
                editor.putFloat(id.toString(), (float) cameraMatrixArray[id]);
            }
        }

        double[] distortionCoefficientsArray = new double[DISTORTION_COEFFICIENTS_SIZE];
        distortionCoefficients.get(0, 0, distortionCoefficientsArray);
        int shift = CAMERA_MATRIX_ROWS * CAMERA_MATRIX_COLS;
        for (Integer i = shift; i < DISTORTION_COEFFICIENTS_SIZE + shift; i++) {
            editor.putFloat(i.toString(), (float) distortionCoefficientsArray[i - shift]);
        }

        editor.commit();
        Log.i(TAG, "Saved camera matrix: " + cameraMatrix.dump());
        Log.i(TAG, "Saved distortion coefficients: " + distortionCoefficients.dump());

        //Write Shared Preferences to File
        File myFile = new File(activity.getApplicationContext().getFilesDir().toString() + CameraCalibrationActivity.DATA_FILEPATH);
//        if (!myFile.exists()) {
//            myFile.mkdir();
//        }
        try {
            FileWriter fw = new FileWriter(myFile);
            PrintWriter pw = new PrintWriter(fw);

            Map<String, ?> prefsMap = sharedPref.getAll();

            for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
                pw.println(entry.getKey() + "," + entry.getValue().toString());
            }

            pw.close();
            fw.close();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public static boolean tryLoad(Activity activity, Mat cameraMatrix, Mat distortionCoefficients) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        if (sharedPref.getFloat("0", -1) == -1) {
            Log.i(TAG, "No previous calibration results found");
            return false;
        }

        double[] cameraMatrixArray = new double[CAMERA_MATRIX_ROWS * CAMERA_MATRIX_COLS];
        for (int i = 0; i < CAMERA_MATRIX_ROWS; i++) {
            for (int j = 0; j < CAMERA_MATRIX_COLS; j++) {
                Integer id = i * CAMERA_MATRIX_ROWS + j;
                cameraMatrixArray[id] = sharedPref.getFloat(id.toString(), -1);
            }
        }
        cameraMatrix.put(0, 0, cameraMatrixArray);
        Log.i(TAG, "Loaded camera matrix: " + cameraMatrix.dump());

        double[] distortionCoefficientsArray = new double[DISTORTION_COEFFICIENTS_SIZE];
        int shift = CAMERA_MATRIX_ROWS * CAMERA_MATRIX_COLS;
        for (Integer i = shift; i < DISTORTION_COEFFICIENTS_SIZE + shift; i++) {
            distortionCoefficientsArray[i - shift] = sharedPref.getFloat(i.toString(), -1);
        }
        distortionCoefficients.put(0, 0, distortionCoefficientsArray);
        Log.i(TAG, "Loaded distortion coefficients: " + distortionCoefficients.dump());

        return true;
    }
}
