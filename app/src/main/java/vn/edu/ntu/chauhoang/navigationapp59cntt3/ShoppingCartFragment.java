package vn.edu.ntu.chauhoang.navigationapp59cntt3;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import vn.edu.ntu.chauhoang.navigationapp59cntt3.controller.ICartController;
import vn.edu.ntu.chauhoang.navigationapp59cntt3.model.Product;

public class ShoppingCartFragment extends Fragment {

    Button btnSubmit, btnClear;
    TextView txtCartInfor, txtTotal;
    ICartController controller;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTotal = view.findViewById(R.id.txtTotal);
        txtCartInfor = view.findViewById(R.id.txtCartInfor);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnClear = view.findViewById(R.id.btnClear);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ShoppingCartFragment.this)
                        .navigate(R.id.action_shoppingCartFragment_to_confirmFragment);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                controller = (ICartController) getActivity().getApplication();
                controller.clearShoppingCart();
                txtCartInfor.setText("");
                Toast.makeText(getActivity(),"Đã xóa giỏ hàng !!!", Toast.LENGTH_SHORT).show();
                txtTotal.setText("Giá tiền = 0");
            }
        });

        ViewCartInfor();
       // view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View view) {
//                NavHostFragment.findNavController(ShoppingCartFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
        //    }
       // });
    }
    private void ViewCartInfor()
    {
        int s = 0;
        ICartController controller = (ICartController) getActivity().getApplication();
        ArrayList<Product> listProduct = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
            for (Product p : listProduct)
            {
                builder.append(p.getName() + ": \t\t" + p.getPrice() + " VND\n");
                s += p.getPrice();
            }

        txtTotal.setText("Tổng tiền: " + new Integer(s).toString() + " vnd");

        if(builder.toString().length()>0)
            txtCartInfor.setText(builder.toString());
        else
            txtCartInfor.setText("Không có mặt hàng nào trong giỏ hàng");
    }
}