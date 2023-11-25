import Head from "next/head";
import Hero from "../components/challengesHero";
import Navbar from "../components/navbar";
import SectionTitle from "../components/sectionTitle";

import Footer from "../components/footer";
import Testimonials from "../components/Founders";
import PopupWidget from "../components/popupWidget";
import ChallengesHero from "../components/challengesHero";

const Home = () => {
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
      <ChallengesHero />
      
      <SectionTitle
        pretitle="Category"
        title="Cryptography">
      </SectionTitle>
      <Testimonials />
      <Testimonials />

      <SectionTitle
        pretitle="Category"
        title="Web">
      </SectionTitle>
      <Testimonials />
      <Testimonials />

      <SectionTitle
        pretitle="Category"
        title="Forensics">
      </SectionTitle>
      <Testimonials />
      <Testimonials />

      <SectionTitle
        pretitle="Category"
        title="Steganography">
      </SectionTitle>
      <Testimonials />
      <Testimonials />
    
      <SectionTitle
        pretitle="Category"
        title="PWN">
      </SectionTitle>
      <Testimonials />
      <Testimonials />
      <Footer />
      <PopupWidget />
    </>
  );
}

export default Home;