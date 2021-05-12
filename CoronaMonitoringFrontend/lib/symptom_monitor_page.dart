import 'package:cs458_project4_flutter/login.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'symtom_page.dart';



class MonitorPageStateless extends StatelessWidget {
  int userId;

  MonitorPageStateless({Key key, this.userId}) : super(key: key);

  static const String _title = 'Covid19 Monitoring';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: _title,
      home: MonitorPage(userId: this.userId),
    );
  }
}

class MonitorPage extends StatefulWidget {
  int userId;

  MonitorPage({this.userId});

  @override
  State<StatefulWidget> createState() => new _State(userId: this.userId);
}



class _State extends State<MonitorPage> {

  int userId;
  var myResponse;
  _State({this.userId});

  void _get_symptoms(int userId) async {
    try {
      var url = Uri.parse('http://localhost:8080/api/symptoms/all/' + userId.toString());

      var response = await http.get(
        url,
        headers: {
          "Content-Type": "application/json",
          //"Access-Control-Allow-Origin": "*",
          //"Access-Control-Allow-Methods":
          //    "POST, GET, OPTIONS, PUT, DELETE, HEAD",
        },
      );
      print('Response status: ${response.statusCode}');
      print('Response body: ${response.body}');
      final res_body = jsonDecode(response.body);
      setState(() {
        this.myResponse = jsonDecode(response.body);
      });
    } catch (e) {
      print(e.message);
    }
  }

  @override
  void initState () {
    super.initState();
    _get_symptoms(this.userId);
  }

  @override
  Widget build(BuildContext context) {
    if (this.myResponse == null) {
      return Scaffold(
          appBar: AppBar(
            title: Text('Monitor Screen'),
          ));
    } else {
      return Scaffold(
          appBar: AppBar(
            title: Text('Monitor Screen'),
          ),
          body: Padding(
              padding: EdgeInsets.all(10),
              child: ListView(
                children: [
                    for (var symptoms in myResponse['data'])
                      Container(
                        margin: new EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),

                        child: ListBody (
                          children: [
                            Container(
                                child: Table(
                                    border: TableBorder.all(width: 1.0, color: Colors.black),
                                    children: [
                                      TableRow(children: [
                                        TableCell(
                                          child: Row(
                                            mainAxisAlignment: MainAxisAlignment.spaceAround,
                                            children: <Widget>[Padding(
                                              padding: EdgeInsets.all(10),
                                              child: RichText(
                                                text: new TextSpan(
                                                  style: new TextStyle(
                                                    fontSize: 28.0,
                                                    color: Colors.black,
                                                  ),
                                                    children: <TextSpan>[
                                                      new TextSpan(text: symptoms['date']),
                                                    ],
                                                  ),
                                              )
                                            )
                                            ],
                                          ),
                                        ),]),
                                    ]
                                )
                            ),
                            for (var symptom_name in [ ["FEVER", "fever"], ["DRY COUGH", "dryCough"], ["TIREDNESS", "tiredness"],
                              ["LOSS OF TASTE", "lossOfTaste"], ["LOSS OF SMELL","lossOfSmell"], ["SORE THROAT", "soreThroat"], ["HEADACHE", "headache"]])
                            Container(
                                child: Table(
                                    border: TableBorder.all(width: 2.0, color: Colors.black),
                                    children: [
                                      TableRow(children: [
                                        TableCell(
                                          child: Row(
                                            mainAxisAlignment: MainAxisAlignment.spaceAround,
                                            children: <Widget>[Padding(
                                              padding: EdgeInsets.all(10),
                                              child: Text(symptom_name[0]),
                                              )
                                            ],
                                          ),
                                        ),
                                        TableCell(
                                            verticalAlignment: TableCellVerticalAlignment.fill,
                                            child: Container (
                                            color: symptoms[symptom_name[1]] ? Colors.red : Colors.green,
                                              child: Row(
                                                mainAxisAlignment: MainAxisAlignment.spaceAround,
                                                children: <Widget>[
                                                  new Text(symptoms[symptom_name[1]] ? 'Exist' : 'Not Exist'),
                                                ],
                                              ),
                                            )
                                        )
                                      ]),
                                    ]
                                )
                            )
                          ],
                        )
                    ),
                  Container(
                      height: 50,
                      padding: EdgeInsets.fromLTRB(10, 10, 10, 0),
                      // ignore: deprecated_member_use
                      child: RaisedButton(
                        textColor: Colors.white,
                        color: Colors.blue,
                        child: Text('Back to Send Symptom Page'),
                        onPressed: () => {
                          Navigator.push(
                              context,
                              MaterialPageRoute(builder: (context) => MyApp(userId: this.userId))),
                        },
                      ))
                ],
              )));
    }
  }
}
