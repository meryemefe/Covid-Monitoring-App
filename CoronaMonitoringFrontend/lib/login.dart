import 'package:cs458_project4_flutter/symptom_monitor_page.dart';
import 'package:flutter/material.dart';
import 'symtom_page.dart';
import 'sign_up.dart';
import 'package:http/http.dart';
import 'dart:convert';

void main() {
  runApp(MyApp2());
}

void _login(TextEditingController nameController,
    TextEditingController passwordController, BuildContext context) async {
  final url = Uri.parse('http://localhost:8080/api/auth/login');
  Map<String, String> headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "POST, GET, OPTIONS, PUT, DELETE, HEAD",
  };

  String request_body = json.encoder.convert(
      {"username": nameController.text, "password": passwordController.text});

  // make POST request
  Response response = await post(url, headers: headers, body: request_body);
  // check the status code for the result
  int statusCode = response.statusCode;
  // this API passes back the id of the new item added to the body
  final Map parsed = json.decode(response.body);
  if (parsed['successful']) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => MyApp(userId: parsed['data']['id'])),
    );
  } else {
      // set up the button
      Widget okButton = FlatButton(
        child: Text("OK"),
        onPressed: () { Navigator.of(context).pop(); },
      );

      // set up the AlertDialog
      AlertDialog alert = AlertDialog(
        title: Text(parsed['errorReason']),
        content: null,
        actions: [
          okButton,
        ],
      );

      // show the dialog
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return alert;
        },
      );
  }

}

class MyApp2 extends StatelessWidget {
  // const MyApp2({Key? key}) : super(key: key);
  static const String _title = 'Flutter Code Sample';
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: _title,
      home: URLPage(),
    );
  }
}

class URLPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => new _State();
}

class _State extends State<URLPage> {
  TextEditingController nameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Covid19 Monitoring App'),
        ),
        body: Padding(
            padding: EdgeInsets.all(10),
            child: ListView(
              children: <Widget>[
                Container(
                    alignment: Alignment.center,
                    padding: EdgeInsets.all(10),
                    child: Text(
                      'Covid19 Monitoring App',
                      style: TextStyle(
                          color: Colors.blue,
                          fontWeight: FontWeight.w500,
                          fontSize: 30),
                    )),
                Container(
                  padding: EdgeInsets.all(10),
                  child: TextField(
                    controller: nameController,
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'User Name',
                    ),
                  ),
                ),
                Container(
                  padding: EdgeInsets.fromLTRB(10, 10, 10, 0),
                  child: TextField(
                    obscureText: true,
                    controller: passwordController,
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'Password',
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
                        child: Text('Login'),
                        onPressed: () {
                          _login(nameController, passwordController, context);
                        })),
                Container(
                    height: 50,
                    padding: EdgeInsets.fromLTRB(10, 10, 10, 0),
                    // ignore: deprecated_member_use
                    child: RaisedButton(
                      textColor: Colors.white,
                      color: Colors.blue,
                      child: Text('Sign Up'),
                      onPressed: () {
                        //signup screen
                        Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => SignupPage()),
                        );
                      },
                    )),
              ],
            )));
  }
}
