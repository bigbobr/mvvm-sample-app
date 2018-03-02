package com.example.test.mvvmsampleapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.test.mvvmsampleapp.api.Constants;
import com.example.test.mvvmsampleapp.service.model.Project;
import com.example.test.mvvmsampleapp.service.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

public class ProjectListViewModel extends AndroidViewModel {
    private final LiveData<List<Project>> projectListObservable;

    @Inject
    public ProjectListViewModel(@NonNull ProjectRepository projectRepository, @NonNull Application application) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = projectRepository.getProjectList(Constants.USER_ID);
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Project>> getProjectListObservable() {
        return projectListObservable;
    }
    
//    public void updateUserID(String userID) {
//        if (!Utils.empty(userID) && !userID.equals(this.userID)) {
//            this.userID = userID;
//            projectListObservable = projectRepository.getProjectList(userID);
//        }
//    }
    
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        updateUserID(s.toString());
    }
    
//    public String getUserID() {
//        return this.userID;
//    }
}
