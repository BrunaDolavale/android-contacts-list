package com.dolavale.bruna.contatos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter {

    List<Model> contactCards;

    public CardsAdapter(List<Model> contactCards) {
        this.contactCards = contactCards;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        return new ContactViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Model model = contactCards.get(position);

        ContactViewHolder viewHolder = (ContactViewHolder) holder;
        viewHolder.name.setText(model.getName());
        viewHolder.city.setText(model.getCity());
        viewHolder.email.setText(model.getEmail());
        viewHolder.phoneNumber.setText(model.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return contactCards.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView phoneNumber;
        public TextView name;
        public TextView email;
        public TextView city;
        public android.widget.Button showDetails;

        public ContactViewHolder(final View itemView) {
            super(itemView);

            phoneNumber = itemView.findViewById(R.id.contact_phoneNumber);
            email = itemView.findViewById(R.id.contact_email);
            name = itemView.findViewById(R.id.contact_name);
            city = itemView.findViewById(R.id.contact_city);
            showDetails = itemView.findViewById(R.id.button_goDetailsContact);

            showDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("email", email.getText().toString());
                    intent.putExtra("city", city.getText().toString());
                    intent.putExtra("phoneNumber", phoneNumber.getText().toString());

                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
