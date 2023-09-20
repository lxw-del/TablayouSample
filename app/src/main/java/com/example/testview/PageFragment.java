package com.example.testview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {
    @LayoutRes int sampleLayoutSrc;
    @LayoutRes int practiceLayoutSrc;

    /**
     * 此碎片的实例化方法
     * @param sampleLayoutSrc
     * @param practiceLayoutSrc
     * @return fragment
     */
    public static PageFragment newInstance(@LayoutRes int sampleLayoutSrc,@LayoutRes int practiceLayoutSrc){
        PageFragment pageFragment = new PageFragment();
        Bundle arg = new Bundle();
        arg.putInt("sampleLayoutSrc",sampleLayoutSrc);
        arg.putInt("practiceLayoutSrc",practiceLayoutSrc);
        pageFragment.setArguments(arg);
        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,container,false);

        //ViewStub 的布局加载示例
        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
        sampleStub.setLayoutResource(sampleLayoutSrc);
        sampleStub.inflate();

        ViewStub practiceStub = (ViewStub) view.findViewById(R.id.practiceStub);
        practiceStub.setLayoutResource(practiceLayoutSrc);
        practiceStub.inflate();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        if (arg != null){
            sampleLayoutSrc = arg.getInt("sampleLayoutSrc");
            practiceLayoutSrc = arg.getInt("practiceLayoutSrc");
        }

    }
}
