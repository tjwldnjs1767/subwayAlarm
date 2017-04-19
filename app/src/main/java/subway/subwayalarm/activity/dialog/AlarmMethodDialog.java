package subway.subwayalarm.activity.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import subway.subwayalarm.activity.MainActivity;


public class AlarmMethodDialog {
    // TODO: 2017-03-29 다이얼로그 호출 부분 추가
    public void dialogAlarmMethod(final MainActivity m) {
        final String items[] = {"진동반복", "진동반복 + 이어폰시 벨소리"};
        AlertDialog.Builder ab = new AlertDialog.Builder(m);

        ab.setTitle("알람 방식")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(m.getApplicationContext(), "dd", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = ab.create();
        alertDialog.show();
    }

    public void dialogAlarmCondition(final MainActivity a) {
        final String items[] = {"도착 1정류장전", "도착 2정류장전",
                "도착 3정류장전", "도착 5정류장전"};
    }
}