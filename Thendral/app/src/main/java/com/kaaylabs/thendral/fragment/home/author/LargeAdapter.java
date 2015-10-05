package com.kaaylabs.thendral.fragment.home.author;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.gson_pojo.ThenralAuthorList;
import com.mayuonline.tamilandroidunicodeutil.TamilUtil;

import java.util.List;

public final class LargeAdapter extends RecyclerView.Adapter<LargeAdapter.ViewHolder> implements BubbleTextGetter
  {
  private static final int SIZE=1000;
  private final  List<ThenralAuthorList> thendralAuthorList;
  public static Context context;
    private final List<String> authorsNameList;

    public LargeAdapter(Context context, List<ThenralAuthorList> thendralAuthorList,List<String> authorsNameList)
    {
    this.thendralAuthorList= thendralAuthorList;
    java.util.Random r=new java.util.Random();

    this.authorsNameList = authorsNameList;
   // for(int i=0;i<24;i++)
    //  items.add(((char)('A'+r.nextInt('Z'-'A')))+" "+Integer.toString(i));
     // items.add(((char)('அ'+r.nextInt('ஒ'-'அ')))+" "+Integer.toString(i));
    java.util.Collections.sort(authorsNameList);

      this.context = context;
    }


    @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
    View view=LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
    return new ViewHolder(view);
    }

  @Override
  public void onBindViewHolder(ViewHolder holder,int position)
    {
    String text=authorsNameList.get(position);
    holder.setText(text);
    }

  @Override
  public String getTextToShowInBubble(final int pos)
    {
    return Character.toString(authorsNameList.get(pos).charAt(0));
    }

  @Override
  public int getItemCount()
    {
    return thendralAuthorList.size();
    }

  public static final class ViewHolder extends RecyclerView.ViewHolder
    {
    private final TextView textView;

    private ViewHolder(View itemView)
      {
      super(itemView);
      this.textView=(TextView)itemView.findViewById(android.R.id.text1);

      }

    public void setText(CharSequence text)
      {
        String name = text.toString();
        int color = context.getResources().getColor(R.color.sub_title_head_color);
        textView.setTextColor(color);
        String eventName = TamilUtil.convertToTamil(TamilUtil.BAMINI, name);
       // textView.setTypeface(ThendralApplication.tf_tamil_font);
      textView.setText(name);
      }
    }
  }
