import Head from "next/head";
import Navbar from "../components/navbar";
import Footer from "../components/footer";
import UserProfileCard from "../components/userProfileCard";
import Pdp from '../public/img/avatar1.jpg';
import background from '../public/img/background.jpg';



const Profile = () => {
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

      <Navbar />

      
      <UserProfileCard ame={'Abdellaoui Mohamed'} score={100000} level={100} team={'Drari'} creationDate={'04/11/2023'} source={Pdp} background={background} />

      
      <Footer />
    </>
  );
}

export default Profile;
