package com.example.rhomie.Controller;

import com.example.rhomie.Models.RequestModel;
import com.example.rhomie.Objects.IItem;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.View.ApprovalOrRejectionActivity;
import com.example.rhomie.View.IRequestView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class RequestController implements Observer {
    private RequestModel model;
    private IRequestView view;

    public RequestController(IRequestView view){
        this.view = view;
        model = new RequestModel();
        model.addObserver(this);
    }

    public void setDetails(ArrayList<String> details){
        String item = details.get(0);
        String user = details.get(1);
        String message = details.get(2);
        String fullName = details.get(3);
        String phoneNumber = details.get(4);
        addRequest(item,user,message,fullName,phoneNumber);
    }
    public void getDetails(String item,String user,String message){
        model.getDetails(item, user, message);
    }

    public void addRequest(String item,String user,String message,String fullName,String phoneNumber){
        Request request = new Request("",item,model.getUser(), message,0,fullName,phoneNumber);
        int requestCode = request.isValid();

        if(requestCode == 1)
            view.OnError("Description is required");
        if(requestCode == -1)
            model.addRequest(item,user,request);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.getClass().equals(ArrayList.class)) {
            ArrayList<String> details = (ArrayList<String>) arg;
            setDetails(details);
        }
        else{
            if((boolean) arg){
                //success
                view.OnSuccess("Successfully sent request");
            }else{
                //failed
                view.OnError("Failed sent request");
            }
        }
    }
}
