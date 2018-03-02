package com.example.test.mvvmsampleapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.example.test.mvvmsampleapp.Utils;
import com.example.test.mvvmsampleapp.api.Constants;
import com.example.test.mvvmsampleapp.service.model.Project;
import com.example.test.mvvmsampleapp.service.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

public class ProjectListViewModel extends AndroidViewModel {
    private LiveData<List<Project>> projectListObservable;
    private String userID;
    private ProjectRepository projectRepository;
    
    private Observer<List<Project>> observer;
    private LifecycleOwner owner;
    
    @Inject
    public ProjectListViewModel(@NonNull ProjectRepository projectRepository, @NonNull Application application) {
        super(application);
    
        this.projectRepository = projectRepository;
        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = projectRepository.getProjectList(Constants.USER_ID);
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Project>> getProjectListObservable() {
        return projectListObservable;
    }
    
    public void updateUserID(String userID) {
        if (!Utils.empty(userID) && !userID.equals(this.userID)) {
            this.userID = userID;
            this.projectListObservable = projectRepository.getProjectList(userID);
        }
    }
    
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        updateUserID(s.toString());
        observe();
    }
    
    public void setObserver(Observer<List<Project>> observer) {
        this.observer = observer;
    }
    
    public void observe() {
        getProjectListObservable().observe(this.owner, this.observer);
    }
    
    public void setOwner(LifecycleOwner owner) {
        this.owner = owner;
    }
    
    public String getUserID() {
        return this.userID;
    }
}
