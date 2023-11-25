import Head from "next/head";
import Navbar from "../components/navbar";
import ChallengesHero from "../components/challengesHero";
import SignInForm from "../components/signInForm";

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
  
        {/* <Navbar />
        <ChallengesHero /> */}
        <SignInForm />
        </>
    );
}

export default SignIn;