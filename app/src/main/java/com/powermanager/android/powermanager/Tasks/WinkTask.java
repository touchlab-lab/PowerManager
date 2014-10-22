package com.powermanager.android.powermanager.Tasks;
import android.content.Context;

import com.powermanager.android.powermanager.DataHelper;
import com.powermanager.android.powermanager.Tasks.API.QuirkyCalls;
/**
 * @author zafrani (david@touchlab.co).
 */
public class WinkTask extends AbstractEventBusTask{
    @Override
    protected void run(Context context) throws Exception{
        response =DataHelper.makeRequestAdapter(context).create(QuirkyCalls.class).wink();
    }
}
