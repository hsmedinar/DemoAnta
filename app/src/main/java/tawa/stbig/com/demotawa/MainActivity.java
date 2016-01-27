package tawa.stbig.com.demotawa;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tawa.stbig.com.demotawa.fragments.FragmentImageList;
import tawa.stbig.com.demotawa.fragments.FragmentImageZoom;
import tawa.stbig.com.demotawa.listener.ListenerImage;
import tawa.stbig.com.demotawa.object.ImageObject;

public class MainActivity extends AppCompatActivity implements ListenerImage {

    static FragmentManager mFragmentManager;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();

        swicthFragment(1, null);
    }


    private void swicthFragment(int screen, Bundle bundle){

        Fragment fragment=null;

        switch(screen){
            case 1 :
                fragment = new FragmentImageList();
                break;
            case 2 :
                fragment = new FragmentImageZoom();
                break;
        }

        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, fragment).commit();


    }

    private void loadFragment(Fragment mFragment){
        if (mFragment != null) {
            mFragmentManager.beginTransaction().replace(R.id.drawer_layout, mFragment).commit();
        }
    }

    @Override
    public void selectedImage(ImageObject imgObject) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("entity",imgObject);
        swicthFragment(2, bundle);
    }


    @Override
    public void returnImage() {
        swicthFragment(1, null);
    }
}
