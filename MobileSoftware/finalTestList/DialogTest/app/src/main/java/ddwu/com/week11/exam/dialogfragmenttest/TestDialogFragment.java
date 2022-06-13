package ddwu.com.week11.exam.dialogfragmenttest;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class TestDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        다이얼로그 생성 및 반환
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("다이얼로그 제목")
                .setMessage("다이얼로그 메시지")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "확인!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "취소!", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog dlg = builder.create();

        return dlg;
    }

}
