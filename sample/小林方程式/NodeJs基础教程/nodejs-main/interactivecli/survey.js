const inquirer = require("inquirer");

const questions = [
  {
    type: "input",
    name: "name",
    message: "What's your name?",
  },
  {
    type: "input",
    name: "email",
    message: "What's your email address?",
    validate: function (value) {
      let pass = value.match(
        /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
      );
      if (pass) {
        return true;
      }
      return "Please enter a valid email address.";
    },
  },
  {
    type: "list",
    name: "computerLanguage",
    message: "Which computer language do you perfer to learn?",
    choices: ["Java", "JavaScript", "Python"],
  },
  {
    type: "checkbox",
    name: "preferredJavaSkills",
    choices: [
      {
        name: "Spring Boot",
      },
      {
        name: "JPA",
      },
      {
        name: "myBatis",
      },
    ],
    when: function (answers) {
      return answers.computerLanguage == "Java";
    },
  },
  {
    type: "checkbox",
    name: "preferredJsSkills",
    choices: [
      {
        name: "Express",
      },
      {
        name: "Sequelize",
      },
      {
        name: "Graphql",
      },
    ],
    when: function (answers) {
      return answers.computerLanguage == "JavaScript";
    },
  },
  {
    type: "checkbox",
    name: "preferredPythonSkills",
    choices: [
      {
        name: "NLP",
      },
      {
        name: "Panda",
      },
      {
        name: "Airflow",
      },
    ],
    when: function (answers) {
      return answers.computerLanguage == "Python";
    },
  },
  {
    type: "list",
    name: "experience",
    message:
      "How many years of experience do you have as a software developer?",
    choices: [
      "less than 1 year",
      "1 - 3 years",
      "3 - 5 years",
      "5 - 10 years",
      "10 - 20 years",
      "more than 20 years",
    ],
  },
];

inquirer.prompt(questions).then((answers) => {
  //you may save the answers into a database here.
  console.log(JSON.stringify(answers, null, "  "));
  console.log("Thank you very much for answering the questions.");
});
