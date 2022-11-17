package edu.uncc.inclass12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GradesFragment.CreateGradeFragmentListener, AddCourseFragment.CreateAddCourseFragmentListener {

    final String TAG = "Sql";
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         db = Room.databaseBuilder(this, AppDatabase.class, "grade.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();

        //db.gradeDao().insertAll(new Grade(001, "Grade 1","Course Number 1", "Course 1", "3"));

        //Grade grade = db.gradeDao().findById(1);
        //Log.d(TAG, "onCreate: " + grade);
//        grade.course_name = "Updated Course Name 1";
//        db.gradeDao().update(grade);


        Log.d(TAG, "onCreate: " + db.gradeDao().getAll());

        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new GradesFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        getSupportFragmentManager().beginTransaction()
                .replace((R.id.rootView), new AddCourseFragment())
                .commit();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void goToAddCourseFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace((R.id.rootView), new AddCourseFragment())
                .commit();
    }

    @Override
    public List<Grade> getGrades() {
        return db.gradeDao().getAll();
    }

    @Override
    public void deleteGrade(Grade g) {
        db.gradeDao().delete(g);
    }

    @Override
    public void addCourse(Grade grade) {
        db.gradeDao().insertAll(grade);
        Log.d(TAG, "addCourse: " + db.gradeDao().getAll());
        getSupportFragmentManager().beginTransaction()
                .replace((R.id.rootView), new GradesFragment())
                .commit();
    }

    @Override
    public void goToGradeFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace((R.id.rootView), new GradesFragment())
                .commit();
    }
}