import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:cs458_project4_flutter/symptom_monitor_page.dart';

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  final int userId;

  MyApp({Key key, this.userId}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(
        title: 'Flutter Demo Home Page',
        userId: this.userId,
      ),
    );
  }
}

class MyHomePage extends StatefulWidget {
  final int userId;

  MyHomePage({Key key, this.title, this.userId}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState(userId: this.userId);
}

class _MyHomePageState extends State<MyHomePage> {
  final int userId;
  bool alertIsShown = false;
  String sendStatus = "";

  _MyHomePageState({this.userId});

  var symptomArr = [false, false, false, false, false, false];
  TextEditingController feverController = TextEditingController();
  // 0: Dry Cough, 1:  Loss of taste, 2:  Loss of smell, 3:  Sore throat, 4:  Headache, 5:  Tiredness, 6:  Fever
  void showAlert(BuildContext context) async {
    if (alertIsShown) return;
    Widget okButton = FlatButton(
      child: Text("OK"),
      onPressed: () {
        Navigator.of(context).pop();
      },
    );

    // set up the AlertDialog

    var url = Uri.parse(
        'http://localhost:8080/api/symptoms/status/' + userId.toString());

    var response = await http.get(
      url,
      headers: {
        "Content-Type": "application/json",
      },
    );
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
    final res_body = jsonDecode(response.body);
    AlertDialog alert = AlertDialog(
      title: Text(sendStatus + res_body["data"]),
      content: null,
      actions: [
        //okButton,
      ],
    );
    // show the dialog
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
    setState(() {
      alertIsShown = true;
    });
  }

  void __Send_symptoms__() async {
    try {
      var url = Uri.parse('http://localhost:8080/api/symptoms');
      double fever = double.parse(feverController.text);
      var requestBody = {
        "dryCough": symptomArr[0],
        "fever": fever > 38,
        "headache": symptomArr[4],
        "lossOfSmell": symptomArr[2],
        "lossOfTaste": symptomArr[1],
        "soreThroat": symptomArr[3],
        "tiredness": symptomArr[5],
        "userId": userId
      };
      print(requestBody);

      var response = await http.post(
        url,
        headers: {
          "Content-Type": "application/json",
        },
        body: json.encoder.convert(requestBody),
      );
      print('Response status: ${response.statusCode}');
      print('Response body: ${response.body}');

      final res_body = jsonDecode(response.body);
      // Navigator.push(
      //   context,
      //   MaterialPageRoute(builder: (context) => MonitorPage()),
      // );
      //print(res_body['originalURL']);
      setState(() {
        if (res_body["successful"]) {
          sendStatus = "Data is sent  : ";
        } else {
          sendStatus = "You already sent  : ";
        }
        alertIsShown = false;
      });
      //showAlert(context);
    } catch (e) {
      print(e.message);
    }
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    WidgetsBinding.instance.addPostFrameCallback((_) => showAlert(context));
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Padding(
          padding: EdgeInsets.all(10),
          child: ListView(children: <Widget>[
            Container(
                alignment: Alignment.center,
                padding: EdgeInsets.all(10),
                child: Text(
                  'Choose your symptoms',
                  style: TextStyle(
                      color: Colors.blue,
                      fontWeight: FontWeight.w500,
                      fontSize: 30),
                )),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(
                  'Dry Cough',
                  style: TextStyle(
                      color: Colors.blue[900],
                      fontWeight: FontWeight.w500,
                      fontSize: 25),
                ),
                Checkbox(
                  value: symptomArr[0],
                  onChanged: (value) {
                    setState(() {
                      symptomArr[0] = value;
                    });
                    print(symptomArr);
                  },
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Loss of taste',
                  style: TextStyle(
                      color: Colors.blue[900],
                      fontWeight: FontWeight.w500,
                      fontSize: 25),
                ),
                Checkbox(
                  value: symptomArr[1],
                  onChanged: (value) {
                    setState(() {
                      symptomArr[1] = value;
                    });
                    print(symptomArr);
                  },
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Loss of smell',
                  style: TextStyle(
                      color: Colors.blue[900],
                      fontWeight: FontWeight.w500,
                      fontSize: 25),
                ),
                Checkbox(
                  value: symptomArr[2],
                  onChanged: (value) {
                    setState(() {
                      symptomArr[2] = value;
                    });
                    print(symptomArr);
                  },
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Sore throat',
                  style: TextStyle(
                      color: Colors.blue[900],
                      fontWeight: FontWeight.w500,
                      fontSize: 25),
                ),
                Checkbox(
                  value: symptomArr[3],
                  onChanged: (value) {
                    setState(() {
                      symptomArr[3] = value;
                    });
                    print(symptomArr);
                  },
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Headache',
                  style: TextStyle(
                      color: Colors.blue[900],
                      fontWeight: FontWeight.w500,
                      fontSize: 25),
                ),
                Checkbox(
                  value: symptomArr[4],
                  onChanged: (value) {
                    setState(() {
                      symptomArr[4] = value;
                    });
                    print(symptomArr);
                  },
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Tiredness',
                  style: TextStyle(
                      color: Colors.blue[900],
                      fontWeight: FontWeight.w500,
                      fontSize: 25),
                ),
                Checkbox(
                  value: symptomArr[5],
                  onChanged: (value) {
                    setState(() {
                      symptomArr[5] = value;
                    });
                    print(symptomArr);
                  },
                ),
              ],
            ),
            Container(
              padding: EdgeInsets.all(10),
              child: TextField(
                controller: feverController,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Fever',
                ),
              ),
            ),
            Container(
                height: 50,
                padding: EdgeInsets.fromLTRB(10, 10, 10, 0),
                // ignore: deprecated_member_use
                child: RaisedButton(
                  textColor: Colors.white,
                  color: Colors.blue,
                  child: Text('Send symptoms'),
                  onPressed: __Send_symptoms__,
                )),
            Container(
                height: 50,
                padding: EdgeInsets.fromLTRB(10, 10, 10, 0),
                // ignore: deprecated_member_use
                child: RaisedButton(
                  textColor: Colors.white,
                  color: Colors.blue,
                  child: Text('See daily symptoms'),
                  onPressed: () => {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) =>
                                MonitorPageStateless(userId: this.userId))),
                  },
                )),
          ])), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
