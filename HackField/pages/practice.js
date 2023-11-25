import Head from "next/head";
import Navbar from "../components/navbar";
import Hero from "../components/hero";
import Container from "../components/container";
import SectionTitle from "../components/sectionTitle";
import ChallengeCard from "../components/challengeCard";
import Footer from "../components/footer";
import { useState } from "react";

const challengesData = [
  { name: 'Mr.Robot', category: 'WEB', points: 10000 },
  { name: 'CryptoPuzzle', category: 'Cryptography', points: 8000 },
  { name: 'ForensicQuest', category: 'Forensics', points: 12000 },
  { name: 'WebExploiter', category: 'WEB', points: 12000 },
  { name: 'CryptoChallenge', category: 'Cryptography', points: 10000 },
  { name: 'ForensicMystery', category: 'Forensics', points: 15000 },
  { name: 'BinaryPuzzle', category: 'PWN', points: 8000 },
  { name: 'NetworkNavigator', category: 'PWN', points: 9000 },
  { name: 'SecureShellMaster', category: 'PWN', points: 11000 },
  { name: 'AIIntruder', category: 'WEB', points: 13000 },
  { name: 'SecureLogAnalysis', category: 'Forensics', points: 9500 },
  { name: 'EnigmaDecoder', category: 'Cryptography', points: 8500 },
  { name: 'FirewallBreaker', category: 'PWN', points: 10000 },
  { name: 'JavaScriptCipher', category: 'WEB', points: 8500 },
  { name: 'DigitalSleuth', category: 'Forensics', points: 11000 },
  { name: 'ReverseEngineeringMaster', category: 'PWN', points: 12000 },
  { name: 'CodeBreaker', category: 'Cryptography', points: 9000 },
  { name: 'MalwareHunter', category: 'Forensics', points: 9500 },
  { name: 'SQLInjectionPro', category: 'WEB', points: 13000 },
  { name: 'BinaryExplosion', category: 'PWN', points: 8000 },
  { name: 'BlockchainExplorer', category: 'Cryptography', points: 10000 },
  { name: 'WiFiCracker', category: 'PWN', points: 8500 },
  { name: 'StealthyTrojan', category: 'PWN', points: 9500 },
  { name: 'CrossSiteScripter', category: 'WEB', points: 12000 },
  { name: 'DarkWebNavigator', category: 'Forensics', points: 10000 },
  { name: 'ShellShockMaster', category: 'PWN', points: 11000 },
  { name: 'QuantumEncryption', category: 'Cryptography', points: 8500 },
  { name: 'MobileForensicExpert', category: 'Forensics', points: 10500 },
  { name: 'APIPenetration', category: 'WEB', points: 13000 },
  { name: 'BinaryNinja', category: 'PWN', points: 9000 },
  { name: 'RansomwareAnalysis', category: 'Forensics', points: 11500 },
  { name: 'SecureRandomizer', category: 'Cryptography', points: 9500 },
  // Add more challenges as needed
];

const Home = () => {
  const [selectedCategory, setSelectedCategory] = useState('All');

  const filteredChallenges = selectedCategory === 'All'
    ? challengesData
    : challengesData.filter(challenge => challenge.category === selectedCategory);

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
      
      <Hero 
        titleFirstPart="Challenges" 
        titleSecondPart="Field" 
        description="Sharpen your hacking skills with mind-bending puzzles and have a blast while boosting your cybersecurity know-how. Are you up for the fun? Let the challenges begin!"
      />

      <Container>
        <div className="mb-8 flex flex-col items-center">
        <SectionTitle pretitle="Select Category ">
            <select
            id="category"
            className="mt-4 p-4 w-full lg:w-64 bg-gray-800 focus:outline-none rounded-lg shadow-md text-white"
            onChange={(e) => setSelectedCategory(e.target.value)}
            >
                <option value="All">All</option>
                <option value="PWN">PWN</option>
                <option value="WEB">WEB</option>
                <option value="Cryptography">Cryptography</option>
                <option value="Forensics">Forensics</option>
            </select>
            </SectionTitle>
        </div>

        <SectionTitle
          pretitle="Category"
          title={` ${selectedCategory.toLowerCase()}`}>
        </SectionTitle>

        <div className="grid gap-10 lg:grid-cols-2 xl:grid-cols-4">
          {filteredChallenges.map((challenge, index) => (
            <ChallengeCard
              key={index}
              name={challenge.name}
              category={challenge.category}
              points={challenge.points}
            />
          ))}
        </div>
      </Container>
      <Footer />
    </>
  );
}

export default Home;
