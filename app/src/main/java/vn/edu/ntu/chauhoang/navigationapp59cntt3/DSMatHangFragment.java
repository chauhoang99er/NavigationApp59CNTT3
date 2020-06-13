package vn.edu.ntu.chauhoang.navigationapp59cntt3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.edu.ntu.chauhoang.navigationapp59cntt3.controller.ICartController;
import vn.edu.ntu.chauhoang.navigationapp59cntt3.model.Product;

public class DSMatHangFragment extends Fragment {

    FloatingActionButton fab;
    RecyclerView rvListMH;
    NavController navcontroller;
    ArrayList<Product> listProduct;
    ProductAdapter adapter;
    RecyclerView rvListProduct;
    ICartController controller;
    ImageView imvCart;
    TextView txtQuantity;
    //ActionBar toolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.mnu_gio_hang, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mnuGioHang:
               callShoppingCartActivity();
                break;
            case R.id.mnu_close:
                DSMatHangFragment.this.getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void callShoppingCartActivity() {
        navcontroller = NavHostFragment.findNavController(DSMatHangFragment.this);
        navcontroller.navigate(R.id.action_DSMatHangFragment_to_shoppingCartFragment);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_ds_mat_hang, container, false);
        rvListMH = view.findViewById(R.id.rvListMH);
        fab = view.findViewById(R.id.fab);
        rvListProduct = view.findViewById(R.id.rvListMH);
        rvListProduct.setLayoutManager(new LinearLayoutManager(DSMatHangFragment.this.getActivity())); //this là mainactivity
        controller = (ICartController) DSMatHangFragment.this.getActivity().getApplication();
        listProduct = controller.getListProduct();
        adapter = new ProductAdapter(listProduct);
        rvListProduct.setAdapter(adapter);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navcontroller = NavHostFragment.findNavController(DSMatHangFragment.this);
        ((MainActivity)getActivity()).controller = navcontroller;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navcontroller.navigate(R.id.action_DSMatHangFragment_to_productFragment);
            }
        });
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtPrice, txtDesc;
        ImageView imBtnCart;
        Product p;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDesc);
            imBtnCart = this.itemView.findViewById(R.id.imBtnCart);
            //Sự kiện nhấn vào giỏ hàng
            imBtnCart.setOnClickListener(this);
        }

        public void bind(Product p) {
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
            //Gán hình
            imBtnCart.setImageResource(R.drawable.ic_action_cart);
        }

        @Override
        public void onClick(View v) {
            //Xác định getApplication = Ten Activity + this.getActivity
            ICartController controller = (ICartController) DSMatHangFragment.this.getActivity().getApplication();
            if (!controller.addToShoppingCart(p)) {
                Toast.makeText(DSMatHangFragment.this.getActivity(),
                        "SP " + p.getName() + " đã có trong giỏ hàng",
                        Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(DSMatHangFragment.this.getActivity(),
                        "Đã thêm sp " + p.getName() + " vào giỏ hàng",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
        ArrayList<Product> listProduct;

        public ProductAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }

        @NonNull
        @Override
        //tạo ra view holder để hiển thị dữ liệu
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            //Chuyển layout đã thiết kế bằng xml thành một đối tượng View
            View view = layoutInflater.inflate(R.layout.product,
                    parent, false);
            return new ProductViewHolder(view);
        }

        //Kêt nối 1 mục dữ liệu trong danh sách với một ViewHolder
        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(listProduct.get(position));
        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }
}