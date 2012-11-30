package net.think_sv.imageviewer;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity implements JSCallback.OnJSEventListner{


private WebView mWebView;
private JSCallback mJSCallback;
private static final String URL = "file:///android_asset/viewer.html";
private static final String JSCALLBACK = "jscallback";

private static final int MENU_ID_LOAD = 1;
private static final int MENU_ID_DELETE = 2;

private static final String SCRIPT_AFTER_LOAD = "javascript:jsif.afterLoad();";

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);        
        
    	this.initControls();	
    	
    }
    
	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		menu.add( Menu.NONE, MENU_ID_LOAD,Menu.NONE, R.string.menu_load ).setIcon( android.R.drawable.ic_menu_gallery );
		return super.onCreateOptionsMenu( menu );
	}

	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case MENU_ID_LOAD:
	    	executeScript(SCRIPT_AFTER_LOAD);
	        return true;
	 
	    }
	    return false;
	}    
    
	private void initControls() {
    	this.mJSCallback = new JSCallback(this);

    	this.mWebView = ( WebView )this.findViewById( R.id.view );
    	this.mWebView.getSettings().setCacheMode( WebSettings.LOAD_NO_CACHE );
    	this.mWebView.getSettings().setJavaScriptEnabled( true );
    	this.mWebView.getSettings().setDomStorageEnabled( true );
    	this.mWebView.setWebChromeClient( this.mWebChromeClient );
    	this.mWebView.addJavascriptInterface( this.mJSCallback, JSCALLBACK );
    	this.mWebView.clearCache( true );
    	this.mWebView.loadUrl( URL );


    }

	
	private WebChromeClient mWebChromeClient = new WebChromeClient() {
		public void onProgressChanged( WebView view, int newProgress ) {
				
		}
	};

	private void executeScript( String format, Object ...args ) {
		String script = String.format( format, args );
		this.mWebView.loadUrl( script );
	} 
	
	public void onJSEvent( int etype ) {
		switch( etype ) {
		case 9999:
            AlertDialog.Builder dlg;
            dlg = new AlertDialog.Builder(this);
            dlg.setTitle("ネイティブアプリからの通知");
            dlg.setMessage("JavaScriptから実行されています。");
            dlg.show();		
	        break;
		}
	}
	
	
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	
	
}

