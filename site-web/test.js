var express = require('express')
const bodyParser = require('body-parser');
var cors = require('cors');
var app = express();
app.use(cors());
app.use(bodyParser.urlencoded({ extended: true }));

app.post('/login', function (req, res) {
    console.log(req.body)
    let email = req.body.email;
    let password = req.body.password;
    if (email.toLowerCase().trim() == 'joe' && password == 'blo') {
        res.status(200).send(true)
    } else {
        res.status(200).send(false)
    }
})
app.get('/login', function (req, res) {
    console.log('get pas supporté')
    res.status(500).send()
})

app.get('/films', function (req, res) {
    console.log(req.query)
    res.status(200).send([
        {code_film:1, titre:'film1'},
        {code_film:2, titre:'film2'},
        {code_film:3, titre:'film3'},
    ])
})

app.get('/films/:code_film', function (req, res) {
    res.status(200).send({
        code_film:1,
        titre:'film1',
        annee:1900,
        pays:['Canada', 'États-Unis'],
        langue:'C#',
        duree:123,
        genres:['Peur', 'Action'],
        realisateur:'Real Lee Sator',
        acteurs:{'Aku Thor':'Perce Honnage', 'Hak Theur':'Perseaux Nages'},
        resume_film:'ouaip',
        affiche:'https://via.placeholder.com/150',
        liens:['a', 'b', 'c'],
    })
})

app.post('/films/:code_film/reserver', function (req, res) {
    //si il reste des copies
    //res.status(200).send('true')
    //sinon forfait permet pas
    res.status(200).send('forfait')
    //sinon
    //res.status(200).send('false')
})

app.listen(3000, function() {
    console.log('It worked!')
    console.log('Listening on http://localhost:3000')
})