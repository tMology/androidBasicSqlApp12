package edu.uncc.inclass12;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import edu.uncc.inclass12.databinding.FragmentGradesBinding;
import edu.uncc.inclass12.databinding.GradeRowItemBinding;

public class GradesFragment extends Fragment {
    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentGradesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGradesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGrades.clear();
        mGrades.addAll(mListener.getGrades());
        gpaHours(mGrades); // This is giving problems with my display.
        binding.recyclerViewGrades.setLayoutManager(new LinearLayoutManager(getContext()));
        gradesAdapter = new GradesAdapter();
        binding.recyclerViewGrades.setAdapter(gradesAdapter);
        getActivity().setTitle("Grades");
    }

    public float gpaHours(ArrayList<Grade> grades){
        //mGrades.clear();
        int hours = 0;
        double gpa = 0.0, gpaQual = 0.0, finGPA=4.0;

        for(Grade grade: grades){
            hours += Integer.parseInt(grade.credit_hours);
            if (grade.course_grade.contentEquals("A")){
                gpaQual = 4;
            }
            if (grade.course_grade.contentEquals("B")){
                gpaQual = 3;
            }
            if (grade.course_grade.contentEquals("C")){
                gpaQual = 2;
            }
            if (grade.course_grade.contentEquals("D")){
                gpaQual = 1;
            }
            if (grade.course_grade.contentEquals("F")){
                gpaQual = 0;
            }
            gpa += (gpaQual* hours);
        }

        if(hours==0){
            binding.textViewGPA.setText("GPA: 4.0");
            binding.textViewHours.setText("Hours: " + Integer.toString(hours));
        }
        else{
            finGPA = gpa/hours;
            DecimalFormat df = new DecimalFormat("#.#####");
            df.format(finGPA);
            binding.textViewGPA.setText("GPA: " + finGPA);
            return (float) finGPA;
        }

        //gradesAdapter.notifyDataSetChanged();
        return 0;
    }




    GradesAdapter gradesAdapter;
    ArrayList<Grade> mGrades = new ArrayList<>();

    class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.GradesViewHolder> {
        @NonNull
        @Override
        public GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            GradeRowItemBinding binding = GradeRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new GradesViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull GradesViewHolder holder, int position) {
            Grade grade = mGrades.get(position);
            holder.setupUI(grade);
        }


        @Override
        public int getItemCount() {
            return mGrades.size();
        }

        // Recycler View-----------------------------------------
        class GradesViewHolder extends RecyclerView.ViewHolder {
            GradeRowItemBinding mBinding;
            Grade mGrade;
            public GradesViewHolder(GradeRowItemBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }

            public void setupUI(Grade grade){

                mGrade = grade;
                mBinding.textViewCourseName.setText(grade.getCourse_name());
                mBinding.textViewCourseNumber.setText(grade.getCourse_number());
                mBinding.textViewCourseHours.setText(String.valueOf(grade.getCredit_hours()));
                mBinding.textViewCourseLetterGrade.setText(grade.getCourse_grade());
                mBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.deleteGrade(mGrade);
                        mGrades.clear();
                        mGrades.addAll(mListener.getGrades());
                        //gradesAdapter.notifyDataSetChanged();
                        gpaHours(mGrades);
                    }
                });
            }
        }

    }


    CreateGradeFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CreateGradeFragmentListener) context;
    }

    interface CreateGradeFragmentListener {
        void goToAddCourseFragment(); //Void reffers we dont return data
        List<Grade> getGrades(); //We need to return data in the form of our list
        void deleteGrade(Grade g);
    }

}