package org.theatertickets.jarno.theatertickets;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class seatsFragment extends Fragment {


    HashMap<Integer, Integer> valinta;
    private View view;
    List<Integer> varaukset;
    String movieIDFromParentActivity;

    public interface TopSectionListener {
    }


    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;
    ImageButton b5;
    ImageButton b6;
    ImageButton b7;
    ImageButton b8;
    ImageButton b9;
    ImageButton b10;
    ImageButton b11;
    ImageButton b12;
    ImageButton b13;
    ImageButton b14;
    ImageButton b15;
    ImageButton b16;
    ImageButton b17;
    ImageButton b18;
    ImageButton b19;
    ImageButton b20;
    ImageButton b21;
    ImageButton b22;
    ImageButton b23;
    ImageButton b24;
    ImageButton b25;
    ImageButton b26;
    ImageButton b27;
    ImageButton b28;
    ImageButton b29;
    ImageButton b30;
    ImageButton b31;
    ImageButton b32;
    ImageButton b33;
    ImageButton b34;
    ImageButton b35;
    ImageButton b36;

    LoginDataBaseAdapter loginDataBaseAdapter;
    List<Integer> reservationsFromDataBase;
    List<ImageButton> listOfImageButtons;

    public interface OnDataPass {
        public void onDataPass(String data);
    }

    OnDataPass dataPasser;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dataPasser = (OnDataPass) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_seats, container, false);
        valinta = new HashMap<Integer, Integer>();
        varaukset = new ArrayList<Integer>();
        listOfImageButtons = new ArrayList<ImageButton>();

        loginDataBaseAdapter = new LoginDataBaseAdapter(view.getContext());
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Reservation activity = (Reservation) getActivity();
        movieIDFromParentActivity = activity.getMovietoFragment();

        activity.getAllInfoMovie();


        reservationsFromDataBase = new ArrayList<Integer>();

        b1 = (ImageButton) view.findViewById(R.id.button1);
        b2 = (ImageButton) view.findViewById(R.id.button2);
        b3 = (ImageButton) view.findViewById(R.id.button3);
        b4 = (ImageButton) view.findViewById(R.id.button4);
        b5 = (ImageButton) view.findViewById(R.id.button5);
        b6 = (ImageButton) view.findViewById(R.id.button6);
        b7 = (ImageButton) view.findViewById(R.id.button7);
        b8 = (ImageButton) view.findViewById(R.id.button8);
        b9 = (ImageButton) view.findViewById(R.id.button9);
        b10 = (ImageButton) view.findViewById(R.id.button10);
        b11 = (ImageButton) view.findViewById(R.id.button11);
        b12 = (ImageButton) view.findViewById(R.id.button12);
        b13 = (ImageButton) view.findViewById(R.id.button13);
        b14 = (ImageButton) view.findViewById(R.id.button14);
        b15 = (ImageButton) view.findViewById(R.id.button15);
        b16 = (ImageButton) view.findViewById(R.id.button16);
        b17 = (ImageButton) view.findViewById(R.id.button17);
        b18 = (ImageButton) view.findViewById(R.id.button18);
        b19 = (ImageButton) view.findViewById(R.id.button19);
        b20 = (ImageButton) view.findViewById(R.id.button20);
        b21 = (ImageButton) view.findViewById(R.id.button21);
        b22 = (ImageButton) view.findViewById(R.id.button22);
        b23 = (ImageButton) view.findViewById(R.id.button23);
        b24 = (ImageButton) view.findViewById(R.id.button24);
        b25 = (ImageButton) view.findViewById(R.id.button25);
        b26 = (ImageButton) view.findViewById(R.id.button26);
        b27 = (ImageButton) view.findViewById(R.id.button27);
        b28 = (ImageButton) view.findViewById(R.id.button28);
        b29 = (ImageButton) view.findViewById(R.id.button29);
        b30 = (ImageButton) view.findViewById(R.id.button30);
        b31 = (ImageButton) view.findViewById(R.id.button31);
        b32 = (ImageButton) view.findViewById(R.id.button32);
        b33 = (ImageButton) view.findViewById(R.id.button33);
        b34 = (ImageButton) view.findViewById(R.id.button34);
        b35 = (ImageButton) view.findViewById(R.id.button35);
        b36 = (ImageButton) view.findViewById(R.id.button36);

        listOfImageButtons.add(b1);
        listOfImageButtons.add(b2);
        listOfImageButtons.add(b3);
        listOfImageButtons.add(b4);
        listOfImageButtons.add(b5);
        listOfImageButtons.add(b6);
        listOfImageButtons.add(b7);
        listOfImageButtons.add(b8);
        listOfImageButtons.add(b9);
        listOfImageButtons.add(b10);
        listOfImageButtons.add(b11);
        listOfImageButtons.add(b12);
        listOfImageButtons.add(b13);
        listOfImageButtons.add(b14);
        listOfImageButtons.add(b15);
        listOfImageButtons.add(b16);
        listOfImageButtons.add(b17);
        listOfImageButtons.add(b18);
        listOfImageButtons.add(b19);
        listOfImageButtons.add(b20);
        listOfImageButtons.add(b21);
        listOfImageButtons.add(b22);
        listOfImageButtons.add(b23);
        listOfImageButtons.add(b24);
        listOfImageButtons.add(b25);
        listOfImageButtons.add(b26);
        listOfImageButtons.add(b27);
        listOfImageButtons.add(b28);
        listOfImageButtons.add(b29);
        listOfImageButtons.add(b30);
        listOfImageButtons.add(b31);
        listOfImageButtons.add(b32);
        listOfImageButtons.add(b33);
        listOfImageButtons.add(b34);
        listOfImageButtons.add(b35);
        listOfImageButtons.add(b36);

        //saadaan nykyiset varaukset
        reservationsFromDataBase = loginDataBaseAdapter.getReservationForMovie(movieIDFromParentActivity);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });
        b36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valinta.get(view.getId()) == 0) {
                    view.setBackgroundResource(R.drawable.oranssituoli21);
                    valinta.put(view.getId(), 1);
                    addReservations(view.getId());
                    passData(addResToString());


                } else if (valinta.get(view.getId()) == 1) {
                    view.setBackgroundResource(R.drawable.vihreatuoli);
                    valinta.put(view.getId(), 0);
                    removeReservation(view.getId());
                    passData(addResToString());
                }
            }
        });


        valinta.put(b1.getId(), 0);
        valinta.put(b2.getId(), 0);
        valinta.put(b3.getId(), 0);
        valinta.put(b4.getId(), 0);
        valinta.put(b5.getId(), 0);
        valinta.put(b6.getId(), 0);
        valinta.put(b7.getId(), 0);
        valinta.put(b8.getId(), 0);
        valinta.put(b9.getId(), 0);
        valinta.put(b10.getId(), 0);
        valinta.put(b11.getId(), 0);
        valinta.put(b12.getId(), 0);
        valinta.put(b13.getId(), 0);
        valinta.put(b14.getId(), 0);
        valinta.put(b15.getId(), 0);
        valinta.put(b16.getId(), 0);
        valinta.put(b17.getId(), 0);
        valinta.put(b18.getId(), 0);
        valinta.put(b19.getId(), 0);
        valinta.put(b20.getId(), 0);
        valinta.put(b21.getId(), 0);
        valinta.put(b22.getId(), 0);
        valinta.put(b23.getId(), 0);
        valinta.put(b24.getId(), 0);
        valinta.put(b25.getId(), 0);
        valinta.put(b26.getId(), 0);
        valinta.put(b27.getId(), 0);
        valinta.put(b28.getId(), 0);
        valinta.put(b29.getId(), 0);
        valinta.put(b30.getId(), 0);
        valinta.put(b31.getId(), 0);
        valinta.put(b32.getId(), 0);
        valinta.put(b33.getId(), 0);
        valinta.put(b34.getId(), 0);
        valinta.put(b35.getId(), 0);
        valinta.put(b36.getId(), 0);


        b1.setBackgroundResource(R.drawable.vihreatuoli);
        b2.setBackgroundResource(R.drawable.vihreatuoli);
        b3.setBackgroundResource(R.drawable.vihreatuoli);
        b4.setBackgroundResource(R.drawable.vihreatuoli);
        b5.setBackgroundResource(R.drawable.vihreatuoli);
        b6.setBackgroundResource(R.drawable.vihreatuoli);
        b7.setBackgroundResource(R.drawable.vihreatuoli);
        b8.setBackgroundResource(R.drawable.vihreatuoli);
        b9.setBackgroundResource(R.drawable.vihreatuoli);
        b10.setBackgroundResource(R.drawable.vihreatuoli);
        b11.setBackgroundResource(R.drawable.vihreatuoli);
        b12.setBackgroundResource(R.drawable.vihreatuoli);
        b13.setBackgroundResource(R.drawable.vihreatuoli);
        b14.setBackgroundResource(R.drawable.vihreatuoli);
        b15.setBackgroundResource(R.drawable.vihreatuoli);
        b16.setBackgroundResource(R.drawable.vihreatuoli);
        b17.setBackgroundResource(R.drawable.vihreatuoli);
        b18.setBackgroundResource(R.drawable.vihreatuoli);
        b19.setBackgroundResource(R.drawable.vihreatuoli);
        b20.setBackgroundResource(R.drawable.vihreatuoli);
        b21.setBackgroundResource(R.drawable.vihreatuoli);
        b22.setBackgroundResource(R.drawable.vihreatuoli);
        b23.setBackgroundResource(R.drawable.vihreatuoli);
        b24.setBackgroundResource(R.drawable.vihreatuoli);
        b25.setBackgroundResource(R.drawable.vihreatuoli);
        b26.setBackgroundResource(R.drawable.vihreatuoli);
        b27.setBackgroundResource(R.drawable.vihreatuoli);
        b28.setBackgroundResource(R.drawable.vihreatuoli);
        b29.setBackgroundResource(R.drawable.vihreatuoli);
        b30.setBackgroundResource(R.drawable.vihreatuoli);
        b31.setBackgroundResource(R.drawable.vihreatuoli);
        b32.setBackgroundResource(R.drawable.vihreatuoli);
        b33.setBackgroundResource(R.drawable.vihreatuoli);
        b34.setBackgroundResource(R.drawable.vihreatuoli);
        b35.setBackgroundResource(R.drawable.vihreatuoli);
        b36.setBackgroundResource(R.drawable.vihreatuoli);

        compareReservations();
        return view;
    }

    private void removeReservation(int id) {
        for (int i = 0; i < varaukset.size(); i++) {
            if (varaukset.get(i) == id) {
                varaukset.remove(i);
                Log.v("poistettu ", "" + id);
            }

        }
    }

    private void addReservations(int id) {
        varaukset.add(id);
        Log.v("varattu penkki", "" + id);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String addResToString() {
        String returnable = "";
        //lista joka otetaan tietokannasta
        for (int j = 0; j < reservationsFromDataBase.size(); j++) {
            returnable = returnable + reservationsFromDataBase.get(j) + ";";
        }


        for (int i = 0; i < varaukset.size(); i++) {
            returnable = returnable + varaukset.get(i) + ";";
        }
        return returnable;
    }
    public void compareReservations(){
        for(int i=0;i<listOfImageButtons.size();i++){
            for(int j=0;j<reservationsFromDataBase.size();j++){
                if(listOfImageButtons.get(i).getId()==reservationsFromDataBase.get(j)) {
                    listOfImageButtons.get(i).setBackgroundResource(R.drawable.punainentuoli);
                    valinta.put(listOfImageButtons.get(i).getId(), 2);
                }
            }
        }


        //Varaukset
        //databasesta
    }

    public void passData(String data){
        dataPasser.onDataPass(data);
    }
}

