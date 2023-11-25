import Head from "next/head";
import SignUpForm from "../components/signUpForm";

const SignIn = () => {
    return (
      <>
        <Head>
          <title>HackField</title>
          <meta
            name="description"
            content="HackField : CTF Plateforme created by Ensa students, and destined to Ensa Student"
          />
          <link rel="icon" href="/favicon.ico" />
        </Head>
  
        <SignUpForm />
        </>
    );
}

export default SignIn;