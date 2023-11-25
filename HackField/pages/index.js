import Head from "next/head";
import Hero from "../components/hero";
import Navbar from "../components/navbar";
import SectionTitle from "../components/sectionTitle";
import { benefitOne, benefitTwo } from "../components/data";
import Benefits from "../components/benefits";
import Footer from "../components/footer";
import Founders from "../components/Founders";
import PopupWidget from "../components/popupWidget";

const Home = () => {
  return (
    <>
      <Head>
        <title>HackField</title>
        <meta
          name="description"
          content="Nextly is a free landing page template built with next.js & Tailwind CSS"
        />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <Navbar />
      <Hero 
        titleFirstPart="Unleash Your Cyber Prowess in the Hack" 
        titleSecondPart="Field" 
        description="Enter the realm of cybersecurity prowess at HackField. Engage in thrilling challenges, sharpen your coding skills, and conquer the virtual battlefield."
      />
      <SectionTitle
        pretitle="Nextly Benefits"
        title=" Why should you use this landing page">
        Nextly is a free landing page & marketing website template for startups
        and indie projects. Its built with Next.js & TailwindCSS. And its
        completely open-source.
      </SectionTitle>
      <Benefits data={benefitOne} />
      <Benefits imgPos="right" data={benefitTwo} />
      
      <SectionTitle
        pretitle="Founders"
        title="Meet the team behind the project!">
        This project embodies a genuine passion for cybersecurity, created with love for the field. Its purpose is to make a lasting impact within our school, offering a platform for future generations to showcase their cybersecurity skills. Authored by a dedicated team, this initiative aims to leave a positive mark and contribute to the ongoing development of cybersecurity expertise among students. 
      </SectionTitle>
      <Founders />
      <Footer />
      <PopupWidget />
    </>
  );
}

export default Home;